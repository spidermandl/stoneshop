package org.goshop.order.pojo;

import java.io.Serializable;

public class GsCartGsp  implements Serializable {
    private Long cartId;

    private Long gspId;

    public Long getCartId() {
        return cartId;
    }

    public void setCartId(Long cartId) {
        this.cartId = cartId;
    }

    public Long getGspId() {
        return gspId;
    }

    public void setGspId(Long gspId) {
        this.gspId = gspId;
    }
}
