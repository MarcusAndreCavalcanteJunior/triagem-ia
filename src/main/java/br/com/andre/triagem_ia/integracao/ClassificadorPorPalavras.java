package br.com.andre.triagem_ia.integracao;

import br.com.andre.triagem_ia.dominio.CategoriaTriagem;
import br.com.andre.triagem_ia.dominio.PrioridadeTriagem;
import br.com.andre.triagem_ia.dominio.ResultadoClassificacao;
import org.springframework.stereotype.Component;

@Component
public class ClassificadorPorPalavras implements ClassificadorTriagem {

    @Override
    public ResultadoClassificacao classificar(String texto) {
        String t = texto.toLowerCase();

        if (t.contains("estorno") || t.contains("reembolso")) {
            return resultado(CategoriaTriagem.ESTORNO_REEMBOLSO, PrioridadeTriagem.ALTA);
        }
        if (t.contains("cobrado") || t.contains("cobranca") || t.contains("cobrança")) {
            return resultado(CategoriaTriagem.COBRANCA_INDEVIDA, PrioridadeTriagem.ALTA);
        }
        if (t.contains("atras")) {
            return resultado(CategoriaTriagem.ATRASO, PrioridadeTriagem.MEDIA);
        }
        if (t.contains("errado") || t.contains("faltou")) {
            return resultado(CategoriaTriagem.PEDIDO_ERRADO_OU_INCOMPLETO, PrioridadeTriagem.MEDIA);
        }
        if (t.contains("frio") || t.contains("estragad") || t.contains("qualidade")) {
            return resultado(CategoriaTriagem.QUALIDADE_PRODUTO, PrioridadeTriagem.MEDIA);
        }
        if (t.contains("aplicativo") || t.contains("travou")) {
            return resultado(CategoriaTriagem.PROBLEMA_APP, PrioridadeTriagem.BAIXA);
        }
        return resultado(CategoriaTriagem.OUTROS, PrioridadeTriagem.BAIXA);
    }

    private ResultadoClassificacao resultado(CategoriaTriagem categoria, PrioridadeTriagem prioridade) {
        return new ResultadoClassificacao(categoria, prioridade);
    }
}
