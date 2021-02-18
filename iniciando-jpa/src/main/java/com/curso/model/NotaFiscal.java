package com.curso.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import java.util.Date;

@Getter
@Setter
@Entity
public class NotaFiscal extends EntidadeBaseInteger {

    @OneToOne
    private Pedido pedido;

    @Lob
    private byte[] xml;

    private Date dataEmissao;
}
