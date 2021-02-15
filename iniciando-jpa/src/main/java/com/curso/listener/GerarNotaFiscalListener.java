package com.curso.listener;

import com.curso.model.Pedido;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

public class GerarNotaFiscalListener {

    @PrePersist
    @PreUpdate
    public void gerar(Pedido pedido) {
        if (pedido.isPago() && pedido.getNotaFiscal() == null) {
            // gerando nfe
        }
    }
}
