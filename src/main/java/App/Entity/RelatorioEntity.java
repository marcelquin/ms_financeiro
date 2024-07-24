package App.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "relatorio")
@Builder
public class RelatorioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany()
    private List<PagamentoEntity> pagamentos;

    private int anoReferencia;

    private int mesReferencia;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime dataAbertura;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime dataFechamento;


    private Double valorVendidoDinheiro;

    private String valorVendidoDinheiroFront;

    private Double valorVendidoPix;

    private String valorVendidoPixFront;

    private Double valorVendidoDebito;

    private String valorVendidoDebitoFront;

    private Double valorVendidoCredito;

    private String valorVendidoCreditoFront;

    private Double totalVendas;

    private String totalVendasFront;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime timeStamp;

}
