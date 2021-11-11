package model;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Venda {

    private Long operacao;
    private Long cliente;
    private Integer quantidadeIngreassos;
    private BigDecimal valorTotal;
    private String status;

    public Venda(long operacao, long cliente, int qtdIngressos, BigDecimal valorTotal) {
        super();
        this.operacao = operacao;
        this.cliente = cliente;
        this.quantidadeIngreassos = qtdIngressos;
        this.valorTotal = valorTotal;
    }
}
