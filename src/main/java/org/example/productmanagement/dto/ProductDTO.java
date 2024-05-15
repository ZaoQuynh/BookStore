package org.example.productmanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.productmanagement.entity.Product;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductDTO {
    private int id;
    private String title;
    private String authors;
    private String publisher;
    private int publisherYear;
    private String genre;
    private String description;
    private int price;
    private int stockQty;
    private int idUser;
    private boolean isDeleted = Boolean.FALSE;
}
