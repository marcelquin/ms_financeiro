package App.DTO;

import java.util.List;

public record RelatorioDiarioDTO(
        int dia,

        String totalVendasDebito,

        String totalVendasCredito,

        String totalVendasDinheiro,

        String totalVendasPix,

        String totalVendas,

        String totalDebitos

        ) {


}
