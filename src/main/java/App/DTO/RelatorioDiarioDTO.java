package App.DTO;

public record RelatorioDiarioDTO(
        int diaReferencia,
        String valorVendidoDinheiro,
        String valorVendidoPix,
        String valorVendidoCredito,
        String valorVendidoDebito,
        String valorTotalVenda
) {
}
