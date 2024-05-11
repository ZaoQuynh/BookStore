package com.example.BookStore.controller;

import com.example.BookStore.dto.CartItemDTO;
import com.example.BookStore.dto.InforDeliveryDTO;
import com.example.BookStore.dto.UserDTO;
import com.example.BookStore.exception.CartItemNotFoundException;
import com.example.BookStore.exception.InforDeliveryNotFoundException;
import com.example.BookStore.service.CartItemService;
import com.example.BookStore.service.CartService;
import com.example.BookStore.service.InforDeliveryService;
import com.example.BookStore.service.UserService;
import com.example.BookStore.utility.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("")
public class CartController {
    private final Logger log = LogManager.getLogger(CartController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private CartService cartService;

    @Autowired
    private InforDeliveryService inforDeliveryService;

    @Autowired
    private CartItemService cartItemService;

    @GetMapping("/cart")
    public String getCartPage(Model model) {
        List<CartItemDTO> cartDTO = getActiveCart(model, "All");
        return "cart/cart";
    }

    @PostMapping("/cart/updateQuantity")
    public String updateQuantity(@RequestParam("id") Long cartItemId,
                                 @RequestParam("qtyChange") String qtyChangeStr,
                                 RedirectAttributes ra) {
        try {
            CartItemDTO cartItem = cartItemService.findById(cartItemId);
            if (cartItem == null) {
                ra.addFlashAttribute("errorMessage", "Item not found in cart.");
            } else {
                cartItem = cartItemService.fixNewQuantity(cartItem, qtyChangeStr);
                if (cartItem.getQty() == 0) {
                    cartItemService.delete(cartItemId);
                } else {
                    cartItemService.update(cartItem);
                }
                ra.addFlashAttribute("successMessage", "Quantity updated successfully.");
            }
        } catch (CartItemNotFoundException e) {
            log.error("Error while updating cartItem with Id: {}", cartItemId, e);
            ra.addFlashAttribute("errorMessage", "Error updating quantity.");
        }
        return "redirect:/cart";
    }

    @PostMapping("/cart/delete")
    public String deleteCartItem(@RequestParam("id") Long cartItemId,
                                 RedirectAttributes ra) {
        try {
            CartItemDTO cartItem = cartItemService.findById(cartItemId);
            if (cartItem == null) {
                ra.addFlashAttribute("errorMessage", "Item not found in cart.");
            } else {
                cartItemService.delete(cartItemId);
                ra.addFlashAttribute("successMessage", "Quantity deleted successfully.");
            }
        } catch (CartItemNotFoundException e) {
            log.error("Error while deleting cartItem with Id: {}", cartItemId, e);
            ra.addFlashAttribute("errorMessage", "Error deleting cartItem.");
        }
        return "redirect:/cart";
    }

    @GetMapping("/inforDelivery")
    public String getInforDeliveryPage(Model model,RedirectAttributes redirectAttributes) {
        List<InforDeliveryDTO> inforDeliveryDTOs;
        Long userId = userService.getCurrentUserId();
        try{
            inforDeliveryDTOs = inforDeliveryService.findByUserId(userId);
        } catch (InforDeliveryNotFoundException e){
            log.error("Error while fetching information delivery with userId: {}", userId, e);
            inforDeliveryDTOs = Collections.emptyList();
        }

        List<CartItemDTO> cartDTO = getActiveCart(model, "Active");
        if(cartDTO.isEmpty()){
            redirectAttributes.addFlashAttribute("errorMessage", "Chưa có sản phẩm hợp lệ trong giỏ hàng");
            return "redirect:/cart";
        }

        model.addAttribute("newInforDelivery", new InforDeliveryDTO());
        model.addAttribute("inforDeliveries", inforDeliveryDTOs);
        return "cart/inforDelivery";
    }

    @PostMapping("/inforDelivery/add")
    public String addInforDelivery(@ModelAttribute("newInforDelivery") InforDeliveryDTO inforDelivery
            ,RedirectAttributes redirectAttributes){
        String error = inforDeliveryService.CheckInput(inforDelivery);
        Long userId = userService.getCurrentUserId();
        if(error!=null){
            log.info(error);
            redirectAttributes.addFlashAttribute("errorMessage", error);
        } else {
            UserDTO currentUser = new UserDTO();
            currentUser.setId(Long.valueOf(1));
            try{
                inforDelivery.setUser(currentUser);
                if(inforDeliveryService.add(inforDelivery) != null){
                    log.info("Information Delivery with UserId {} added successfully!", userId);
                    redirectAttributes.addFlashAttribute("successMessage", "Thêm thông tin thành công");
                } else {
                    log.error("Information Delivery added unsuccessfully!");
                    redirectAttributes.addFlashAttribute("errorMessage", "Thêm thông tin không thành công");
                }
            } catch (Exception e) {
                log.error(e.getMessage());
                redirectAttributes.addFlashAttribute("errorMessage", "Thêm thông tin không thành công");
            }
        }
        return "redirect:/inforDelivery";
    }

    @PostMapping("/methodPayment")
    public String getMethodPaymentPage(@RequestParam(name="selectedInforDelivery", required=false) Long inforDeliveryId
            ,Model model, RedirectAttributes redirectAttributes) {
        if(inforDeliveryId == null){
            redirectAttributes.addFlashAttribute("errorMessage", "Vui lòng chọn thông tin giao hàng");
            return "redirect:/inforDelivery";
        }

        List<CartItemDTO> cartDTO = getActiveCart(model, "Active");
        model.addAttribute("inforDeliveryId", inforDeliveryId);
        return "cart/methodPayment";
    }

    private List<CartItemDTO> getActiveCart( Model model, String status) {
        List<CartItemDTO> cartDTO;
        Long userId = userService.getCurrentUserId();

        try {
            if(status.equals("All"))
                cartDTO = cartService.findByCustomerId(userId);
            else
                cartDTO = cartService.findActiveByCustomerId(userId);
        } catch (CartItemNotFoundException e) {
            log.error("Error while fetching cart with userId: {}", userId, e);
            cartDTO = Collections.emptyList();
        }

        model.addAttribute("cart", cartDTO);
        model.addAttribute("totalPrice",
                Utils.formatCurrencyVietnamese(
                        cartService.totalPriceOfCart(cartDTO)));
        return cartDTO;
    }

    @PostMapping("/payment")
    public String payment(@RequestParam("inforDeliveryId") Long inforDeliveryId
            , @RequestParam("selectedPayment") String methodPayment, RedirectAttributes redirectAttributes) {
        if(methodPayment == null){
            redirectAttributes.addFlashAttribute("errorMessage", "Vui lòng chọn phương thức thanh toán.");
            return "redirect:/methodPayment";
        }
        return "cart/success";
    }
}
