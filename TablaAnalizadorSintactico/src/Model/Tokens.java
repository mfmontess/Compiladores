package Model;

public class Tokens {

	private String token1, token2, token3, token4, token5, token6, token7,
			token8, token9, token10;
	private String token11, token12, token13, token14, token15, token16,
			token17, token18, token19, token20;
	private String token21, token22, token23, token24, token25, token26,
			token27;
	private String lexema1, lexema2, lexema3, lexema4, lexema5, lexema6,
			lexema7, lexema8, lexema9, lexema10;
	private String lexema11, lexema12, lexema13, lexema14, lexema15, lexema16,
			lexema17, lexema18, lexema19, lexema20;
	private String lexema21, lexema22, lexema23, lexema24, lexema25, lexema26,
			lexema27;
	private String indicador;
	public Object[][] datos;

	public Tokens() {

		token1 = "programa";
		token2 = "int";
		token3 = "char";
		token4 = "float";
		token5 = "leer";
		token6 = "imprimir";
		token7 = "+";
		token8 = "-";
		token9 = "*";
		token10 = "/";
		token11 = "=";
		token12 = "terminar";
		token13 = "mientras";
		token14 = "si";
		token15 = "no";
		token16 = "sino";
		token17 = "\"";
		token18 = ";";
		token19 = "(";
		token20 = ")";
		token21 = "{";
		token22 = "}";
		token23 = "&";
		token24 = "&&";
		token25 = "|";
		token26 = "||";
		token27 = ",";

		lexema1 = "programa";
		lexema2 = "int";
		lexema3 = "char";
		lexema4 = "float";
		lexema5 = "leer";
		lexema6 = "imprimir";
		lexema7 = "+";
		lexema8 = "-";
		lexema9 = "*";
		lexema10 = "/";
		lexema11 = "=";
		lexema12 = "terminar";
		lexema13 = "mientras";
		lexema14 = "si";
		lexema15 = "no";
		lexema16 = "sino";
		lexema17 = "\"";
		lexema18 = ";";
		lexema19 = "(";
		lexema20 = ")";
		lexema21 = "{";
		lexema22 = "}";
		lexema23 = "&";
		lexema24 = "&&";
		lexema25 = "";
		lexema26 = "||";
		lexema27 = ",";

		indicador = "Si";

	}

	public Object[][] verificar() {

		Object[][] data = { { lexema1, token1, indicador },
				{ lexema2, token2, indicador }, { lexema3, token3, indicador },
				{ lexema4, token4, indicador }, { lexema5, token5, indicador },
				{ lexema6, token6, indicador }, { lexema7, token7, indicador },
				{ lexema8, token8, indicador }, { lexema9, token9, indicador },
				{ lexema10, token10, indicador },
				{ lexema11, token11, indicador },
				{ lexema12, token12, indicador },
				{ lexema13, token13, indicador },
				{ lexema14, token14, indicador },
				{ lexema15, token15, indicador },
				{ lexema16, token16, indicador },
				{ lexema17, token17, indicador },
				{ lexema18, token18, indicador },
				{ lexema19, token19, indicador },
				{ lexema20, token20, indicador },
				{ lexema21, token21, indicador },
				{ lexema22, token22, indicador },
				{ lexema23, token23, indicador },
				{ lexema24, token24, indicador },
				{ lexema25, token25, indicador },
				{ lexema26, token26, indicador },
				{ lexema27, token27, indicador }, };

		return data;

	}

}