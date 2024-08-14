package App.DTO;

import java.util.List;

public record RelatorioMensalDTO(
        String dataReferencia,

        String totalVendasDebito,

        String totalVendasCredito,

        String totalVendasDinheiro,

        String totalVendasPix,

        String totalVendas,

        String totalDebitos,
        List<PedidosDTO> vendasRealizadas
        ) {


}
