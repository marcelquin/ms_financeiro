package App.DTO;

import App.Enum.FORMAPAGAMENTO;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;
import java.util.List;

public record PedidosDTO(

        String nomeCLiente,

        String documento,
        List<String> itens,

        Double valor,
        @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
        LocalDateTime dataVenda,
        @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
        LocalDateTime dataPagamento,
        FORMAPAGAMENTO formapagamento,
        int parcelas

) {
}
