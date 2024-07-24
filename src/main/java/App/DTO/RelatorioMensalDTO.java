package App.DTO;

public record RelatorioMensalDTO(
        int mesReferencia,
        String valorVendidoDinheiro,
        String valorVendidoPix,
        String valorVendidoCredito,
        String valorVendidoDebito,
        String valorTotalVenda

) {
}
