package br.com.andre.triagem_ia.integracao;

import br.com.andre.triagem_ia.dominio.ResultadoClassificacao;

public interface ClassificadorTriagem {

    ResultadoClassificacao classificar(String texto);
}
