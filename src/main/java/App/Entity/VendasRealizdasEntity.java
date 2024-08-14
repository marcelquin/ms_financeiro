package App.Entity;

import App.Enum.StatusPagamento;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Table(name = "VendasRealizadas")
public class VendasRealizdasEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nomeCLiente;

    private String documento;

    private String codigo;

    private List<String> itens;

    private Double valorTotal;

    private StatusPagamento statusPagamento;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime dataPedido;

    @OneToOne
    @JoinColumn(name = "pagamentoEntity_id", referencedColumnName = "id")
    private PagamentoEntity pagamento;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime timeStamp;
}
