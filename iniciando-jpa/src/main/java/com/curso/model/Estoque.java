package com.curso.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Getter
@Setter
@Entity
public class Estoque extends EntidadeBaseInteger {

    private Integer produtoId;

    private Integer quantidade;
}
