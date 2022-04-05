package br.com.jcv.backend.charter;

import java.util.HashMap;
import java.util.Map;


/*
https://chart.googleapis.com/chart?cht=map&chs=250x500&chtt=Titulo+do+grafico&chld=BR|BR-DF|BR-AC|BR-AL|BR-AP|BR-AM|B

R-BA|BR-CE|BR-ES|BR-GO|BR-MA|BR-MT|BR-MS|BR-MG|BR-PA|BR-PB|BR-PR|BR-PE|BR-PI|BR-RJ|BR-RN|BR-RS|BR-RO|BR-RR|BR-SC|BR-S

P|BR-SE|BR-TO&chco=FFFFFF|000000|FFDEAD|1E90FF|00BFFF|6B8E23|BDB76B|FA8072|FF8C00|F08080|00FF7F|006400|7FFFD4|00CED1|

E6E6FA|FFA500|8B2252|AB82FF|FF6A6A|828282|CD661D|EEB422|8B4726|FFFF00|00FA9A|FF4500|00CED1|A0522D|CD5C5C&chm=fBR,0000

00,0,0,10,15,v|fDF,000000,0,1,10,15,v|fAC,000000,0,2,10,15,v|fAL,000000,0,3,10,15,v|fAP,000000,0,4,10,15,v

ATENCAO PARA A PRIMEIRA COR NO PARAMETRO chco (TEM QUE SER FFFFFF), vai ter uma ocorrencia a +
*/


public class UfMapBrasilInfo {

	public static final Map<String, MapInfo> mapInfo = new HashMap<String, MapInfo>();

	public  static final String BR = "BR"; 
	private static final String DF = "DF"; 
	private static final String AC = "AC"; 
	private static final String AL = "AL"; 
	private static final String AP = "AP"; 
	private static final String AM = "AM"; 
	private static final String BA = "BA"; 
	private static final String CE = "CE"; 
	private static final String ES = "ES"; 
	private static final String GO = "GO"; 
	private static final String MA = "MA"; 
	private static final String MT = "MT"; 
	private static final String MS = "MS"; 
	private static final String MG = "MG"; 
	private static final String PA = "PA"; 
	private static final String PB = "PB"; 
	private static final String PR = "PR"; 
	private static final String PE = "PE"; 
	private static final String PI = "PI"; 
	private static final String RJ = "RJ"; 
	private static final String RN = "RN"; 
	private static final String RS = "RS"; 
	private static final String RO = "RO"; 
	private static final String RR = "RR"; 
	private static final String SC = "SC"; 
	private static final String SP = "SP"; 
	private static final String SE = "SE"; 
	private static final String TO = "TO"; 
	
	private static final String BR_DF = "BR-DF";
	private static final String BR_AC = "BR-AC";
	private static final String BR_AL = "BR-AL";
	private static final String BR_AP = "BR-AP";
	private static final String BR_AM = "BR-AM";
	private static final String BR_BA = "BR-BA";
	private static final String BR_CE = "BR-CE";
	private static final String BR_ES = "BR-ES";
	private static final String BR_GO = "BR-GO";
	private static final String BR_MA = "BR-MA";
	private static final String BR_MT = "BR-MT";
	private static final String BR_MS = "BR-MS";
	private static final String BR_MG = "BR-MG";
	private static final String BR_PA = "BR-PA";
	private static final String BR_PB = "BR-PB";
	private static final String BR_PR = "BR-PR";
	private static final String BR_PE = "BR-PE";
	private static final String BR_PI = "BR-PI";
	private static final String BR_RJ = "BR-RJ";
	private static final String BR_RN = "BR-RN";
	private static final String BR_RS = "BR-RS";
	private static final String BR_RO = "BR-RO";
	private static final String BR_RR = "BR-RR";
	private static final String BR_SC = "BR-SC";
	private static final String BR_SP = "BR-SP";
	private static final String BR_SE = "BR-SE";
	private static final String BR_TO = "BR-TO";

	static {
		mapInfo.put(BR, new MapInfo(BR, BR   , "000000"));
		mapInfo.put(DF, new MapInfo(DF, BR_DF, "FFDEAD"));
		mapInfo.put(AC, new MapInfo(AC, BR_AC, "1E90FF"));
		mapInfo.put(AL, new MapInfo(AL, BR_AL, "00BFFF"));
		mapInfo.put(AP, new MapInfo(AP, BR_AP, "6B8E23"));
		mapInfo.put(AM, new MapInfo(AM, BR_AM, "BDB76B"));
		mapInfo.put(BA, new MapInfo(BA, BR_BA, "FA8072"));
		mapInfo.put(CE, new MapInfo(CE, BR_CE, "FF8C00"));
		mapInfo.put(ES, new MapInfo(ES, BR_ES, "F08080"));
		mapInfo.put(GO, new MapInfo(GO, BR_GO, "00FF7F"));
		mapInfo.put(MA, new MapInfo(MA, BR_MA, "006400"));
		mapInfo.put(MT, new MapInfo(MT, BR_MT, "7FFFD4"));
		mapInfo.put(MS, new MapInfo(MS, BR_MS, "00CED1"));
		mapInfo.put(MG, new MapInfo(MG, BR_MG, "E6E6FA"));
		mapInfo.put(PA, new MapInfo(PA, BR_PA, "FFA500"));
		mapInfo.put(PB, new MapInfo(PB, BR_PB, "8B2252"));
		mapInfo.put(PR, new MapInfo(PR, BR_PR, "AB82FF"));
		mapInfo.put(PE, new MapInfo(PE, BR_PE, "FF6A6A"));
		mapInfo.put(PI, new MapInfo(PI, BR_PI, "828282"));
		mapInfo.put(RJ, new MapInfo(RJ, BR_RJ, "CD661D"));
		mapInfo.put(RN, new MapInfo(RN, BR_RN, "EEB422"));
		mapInfo.put(RS, new MapInfo(RS, BR_RS, "8B4726"));
		mapInfo.put(RO, new MapInfo(RO, BR_RO, "FFFF00"));
		mapInfo.put(RR, new MapInfo(RR, BR_RR, "00FA9A"));
		mapInfo.put(SC, new MapInfo(SC, BR_SC, "FF4500"));
		mapInfo.put(SP, new MapInfo(SP, BR_SP, "00CED1"));
		mapInfo.put(SE, new MapInfo(SE, BR_SE, "A0522D"));
		mapInfo.put(TO, new MapInfo(TO, BR_TO, "CD5C5C"));
	}
	
}