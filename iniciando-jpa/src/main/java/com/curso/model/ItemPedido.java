package com.curso.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;

@Getter
@Setter
@Entity
public class ItemPedido extends EntidadeBaseInteger {

    private Integer pedidoId;

    private Integer produtoId;

    private BigDecimal precoProduto;

    private Integer quantidade;

    @ManyToOne
    private Pedido pedido;

    @ManyToOne
    private Produto produto;
}
