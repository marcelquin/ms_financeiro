package App.DTO;

import java.util.List;

public record RelatorioAnualDTO(
        int ano,

        String totalVendasDebito,

        String totalVendasCredito,

        String totalVendasDinheiro,

        String totalVendasPix,

        String totalVendas,

        String totalDebitos

        ) {


}
