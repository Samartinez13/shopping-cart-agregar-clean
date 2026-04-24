package isi.shoppingCart.usecases.services;

import isi.shoppingCart.entities.CartItem;
import javafx.application.Application;
import javafx.stage.Stage;
import isi.shoppingCart.entities.Cart;
import isi.shoppingCart.entities.Product;
import isi.shoppingCart.usecases.ports.CartRepository;
import isi.shoppingCart.usecases.ports.ProductRepository;

import java.util.Collections;
import java.util.List;

public class ConfirmarCarritoUseCase {
    private CartRepository cartRepository;
    private ProductRepository productRepository;

    public ConfirmarCarritoUseCase(CartRepository cartRepository,
                                   ProductRepository productRepository){
        this.cartRepository= cartRepository;
        this.productRepository= productRepository;}

    public String verificar(){
        Cart cart= cartRepository.getCart();
        if (cart.isEmpty()){
            return "Error";
        }
        List<CartItem> items = cart.getItems();
            int i;
            for (i = 0; i < items.size(); i++) {
                CartItem item = items.get(i);
                Product product= productRepository.findById(item.getProduct().getId());
                int j;
                for (j=0; j < item.getQuantity(); j++){
                product.decreaseAvailableQuantity();}
            }
        cart.clear();
        cartRepository.save(cart);
        return "";
    }
    }

