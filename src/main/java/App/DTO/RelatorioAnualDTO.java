package App.DTO;

public record RelatorioAnualDTO(
        int anoReferencia,
        String valorVendidoDinheiro,
        String valorVendidoPix,
        String valorVendidoCredito,
        String valorVendidoDebito,
        String valorTotalVenda
) {
}
