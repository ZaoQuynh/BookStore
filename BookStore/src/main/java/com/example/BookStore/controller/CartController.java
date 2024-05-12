package com.example.BookStore.controller;

import com.example.BookStore.dto.*;
import com.example.BookStore.entity.Payment;
import com.example.BookStore.exception.CartItemNotFoundException;
import com.example.BookStore.exception.InforDeliveryNotFoundException;
import com.example.BookStore.service.*;
import com.example.BookStore.utility.Utils;
import jakarta.servlet.http.HttpSession;
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
    private OrderService orderService;

    @Autowired
    private CartItemService cartItemService;

    @Autowired
    private ProductService productService;

    @GetMapping("/cart")
    public String getCartPage(Model model) {
        List<CartItemDTO> cart = Collections.emptyList();;
        List<CartItemDTO> activeCart = Collections.emptyList();;
        Long userId = userService.getCurrentUserId();
        try {
            cart = cartService.findByCustomerId(userId);
            activeCart = cartService.findActiveByCustomerId(userId);
        } catch (CartItemNotFoundException e) {
            log.error("Error while fetching cart with userId: {}", userId, e);
        }

        model.addAttribute("cart", cart);
        model.addAttribute("activeCart", activeCart);
        model.addAttribute("totalPrice",
                Utils.formatCurrencyVietnamese(
                        cartService.totalPriceOfCart(activeCart)));
        return "cart/cart";
    }


    @PostMapping("/cart/add")
    public String addCartItem(@RequestParam("productId") Long productId,
                              @RequestParam("productQty") String qtyStr,
                              RedirectAttributes ra){
        try {
            ProductDTO product = productService.findById(productId);
            UserDTO customer = userService.findById(userService.getCurrentUserId());
            int newQty = Utils.toInt(qtyStr);

            CartItemDTO cartItem = new CartItemDTO();
            cartItem.setProduct(product);
            cartItem.setCustomer(customer);
            cartItem.setQty(cartItemService.checkValue(cartItem, newQty));

            int bonusQty = cartService.addCart(cartItem);
            if(bonusQty>0){
                ra.addFlashAttribute("successMessage", "Đã thêm " + bonusQty + " sản phẩm vào giỏ hàng.");
            } else if (bonusQty == 0){
                ra.addFlashAttribute("errorMessage", "Sản phẩm trong giỏ hàng sẽ vượt quá số lượng trong kho.");
            }
        } catch (Exception e){
            log.error(e.getMessage());
            ra.addFlashAttribute("errorMessage", "Thêm sản phẩm vào giỏ hàng thất bại.");
        }
        return "redirect:/product/" + productId;
    }

    @PostMapping("/cart/updateQuantity")
    public String updateQuantity(@RequestParam("id") Long cartItemId,
                                 @RequestParam("qtyChangeInput") String qtyChangeStr,
                                 @RequestParam("event") String event,
                                 RedirectAttributes ra) {
        try {
            int newQty = Utils.toInt(qtyChangeStr);
            CartItemDTO cartItem = cartItemService.findById(cartItemId);
            if(newQty!=0)
                if (cartItem == null) {
                    ra.addFlashAttribute("errorMessage", "Sản phẩm không có trong giỏ hàng!");
                } else {
                    cartItemService.updateQuantity(cartItem, newQty, event);
                    ra.addFlashAttribute("successMessage", "Cập nhật sản phẩm thành công.");
                }
        } catch (CartItemNotFoundException e) {
            log.error("Error while updating cartItem with Id: {}", cartItemId, e);
            ra.addFlashAttribute("errorMessage", "Cập nhật sản phẩm thất bại.");
        }
        return "redirect:/cart";
    }

    @PostMapping("/cart/delete")
    public String deleteCartItem(@RequestParam("id") Long cartItemId,
                                 RedirectAttributes ra) {
        try {
            cartItemService.delete(cartItemId);
            ra.addFlashAttribute("successMessage", "Xóa sản phẩm thành công.");
        } catch (CartItemNotFoundException e) {
            log.error("Error while deleting cartItem with Id: {}", cartItemId, e);
            ra.addFlashAttribute("Xóa sản phẩm thất bại.");
        }
        return "redirect:/cart";
    }

    @GetMapping("/inforDelivery")
    public String getInforDeliveryPage(Model model,RedirectAttributes redirectAttributes) {
        List<InforDeliveryDTO> inforDeliveryDTOs = Collections.emptyList();
        List<CartItemDTO> cart = Collections.emptyList();;
        Long userId = userService.getCurrentUserId();

        try{
            inforDeliveryDTOs = inforDeliveryService.findByUserId(userId);
            cart = cartService.findActiveByCustomerId(userId);
        } catch (InforDeliveryNotFoundException e){
            log.error("Error while fetching information delivery with userId: {}", userId, e);
        } catch (CartItemNotFoundException e){
            log.error("Error while fetching cart with userId: {}", userId, e);
        }

        model.addAttribute("cart", cart);
        model.addAttribute("totalPrice",
                Utils.formatCurrencyVietnamese(
                        cartService.totalPriceOfCart(cart)));
        model.addAttribute("newInforDelivery", new InforDeliveryDTO());
        model.addAttribute("inforDeliveries", inforDeliveryDTOs);
        return "cart/inforDelivery";
    }

    @PostMapping("/inforDelivery/add")
    public String addInforDelivery(@ModelAttribute("newInforDelivery") InforDeliveryDTO inforDelivery
            , RedirectAttributes redirectAttributes){
        String error = inforDeliveryService.CheckInput(inforDelivery);
        if(error != null){
            log.info(error);
            redirectAttributes.addFlashAttribute("errorMessage", error);
            return "redirect:/inforDelivery";
        }

        Long userId = userService.getCurrentUserId();
        UserDTO currentUser = userService.findById(userId);

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

        return "redirect:/inforDelivery";
    }

    @PostMapping("/checkInforDelivery")
    public String getMethodPaymentPage(@RequestParam(name="selectedInforDelivery", required=false) Long inforDeliveryId
            ,Model model, RedirectAttributes redirectAttributes, HttpSession session) {
        if(inforDeliveryId == null){
            redirectAttributes.addFlashAttribute("errorMessage", "Vui lòng chọn thông tin giao hàng");
            return "redirect:/inforDelivery";
        }

        session.setAttribute("inforDeliveryId", inforDeliveryId);
        return "redirect:/methodPayment";
    }

    @GetMapping("/methodPayment")
    public String getMethodPaymentPage(Model model){
        List<CartItemDTO> cart = Collections.emptyList();
        Long userId = userService.getCurrentUserId();

        try {
            cart = cartService.findActiveByCustomerId(userId);
        } catch (CartItemNotFoundException e) {
            log.error("Error while fetching cart with userId: {}", userId, e);
        }

        model.addAttribute("cart", cart);
        model.addAttribute("totalPrice",
                Utils.formatCurrencyVietnamese(
                        cartService.totalPriceOfCart(cart)));
        return "cart/methodPayment";
    }

    @PostMapping("/payment")
    public String payment(@RequestParam(name = "selectedMethodPayment", required = false) String methodPayment,
                          RedirectAttributes redirectAttributes, HttpSession session) {
        if(Utils.isNullOrEmpty(methodPayment)){
            redirectAttributes.addFlashAttribute("errorMessage", "Vui lòng chọn phương thức thanh toán.");
            return "redirect:/methodPayment";
        }

        List<CartItemDTO> cart;
        Long userId = userService.getCurrentUserId();
        Long inforDeliveryId = (Long) session.getAttribute("inforDeliveryId");
        System.out.printf(inforDeliveryId.toString());

        try {
            cart = cartService.findActiveByCustomerId(userId);
            List<OrderDTO> orderDTOs = orderService.convertCartToOrder(cart);

            PaymentDTO payment = new PaymentDTO(Payment.EPaymentMethod.valueOf(methodPayment),
                    Payment.EPaymentStatus.UNPAID, null);
            InforDeliveryDTO inforDelivery = inforDeliveryService.findById(inforDeliveryId);

            orderDTOs.forEach(item -> {
                item.setPayment(payment);
                item.setInforDelivery(inforDelivery);
                orderService.add(item);
            });
            cartService.delete(cart);
            return "redirect:/success";
        } catch (CartItemNotFoundException e) {
            log.error("Error while fetching cart with userId: {}", userId, e);
            redirectAttributes.addFlashAttribute("errorMessage", "Không tìm thấy giỏ hàng.");
        } catch(Exception e){
            log.error(e.getMessage());
            redirectAttributes.addFlashAttribute("errorMessage", "Thanh toán thất bại.");
        }

        return "redirect:/methodPayment";
    }

    @GetMapping("/success")
    public String getMethodPaymentPage(){
        return "cart/success";
    }

}
