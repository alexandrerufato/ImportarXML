/* Autor: Alexandre Rufato
 * 30/06/2019
 */

package bo;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;

import dao.NotaDao;
import dao.interfaces.INotaDao;
import sefaz.TNfeProc;
  
public class NotaBo {  
  
	private static final String CAMINHO = "D:\\xml\\";
	
    
	public void incluir() throws Exception {
		INotaDao dao = new NotaDao();
		for(TNfeProc nota : obterDadosNota()) {
			String retorno = dao.incluir(nota);
			if(retorno.equalsIgnoreCase("ok")) {
				info("Nota com a chave " + nota.getProtNFe().getInfProt().getChNFe() + " adicionada!");
			}
		}
		info("\n TODAS AS NOTAS ADICIONADAS!");
	}
	
    private List<TNfeProc> obterDadosNota() {
    	List<TNfeProc> lista  = new ArrayList<>();
    	 try {
    		 for(String xml : lerXML()) {
    			 if(xml != null) {
    				 TNfeProc wNfe = getNFe(xml);
    				 if (wNfe != null) {  
    					 lista.add(wNfe);
    				 }
    			 }
    		 }
         } catch (Exception e) {  
             error(e.toString());  
         }  
    	 return lista;
    }
    
    public TNfeProc getNFe(String xml) throws Exception{      
        try {      
            JAXBContext context = JAXBContext.newInstance(TNfeProc.class);      
            Unmarshaller unmarshaller = context.createUnmarshaller();      
            TNfeProc nfe = unmarshaller.unmarshal(new StreamSource(new StringReader(xml)), TNfeProc.class).getValue();      
            return nfe;
        } catch (JAXBException ex) {      
            throw new Exception(ex.toString());      
        }      
    }      
    
    private List<String> lerXML() throws IOException {  
        String linha = "";  
        File file = new File(CAMINHO);
    	File[] arquivos = file.listFiles(); //retorna um array de Files
    	List<String> lista = new ArrayList<String>();

    	for(File f : arquivos){
    		StringBuilder xml = new StringBuilder();
    		if(f.isFile() && f.getName().contains(".xml")) { // Valida se é um arquivo e se tem uma extensao xml
    			BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(f)));
    			while ((linha = in.readLine()) != null) {  
    				xml.append(linha);  
    			}
    			lista.add(xml.toString());
    			in.close();
    		}
    	}
    	return lista;  
    }  
  
    private static void info(String log) {  
        System.out.println("INFO: " + log);  
    }  
  
    private void error(String log) {  
        System.out.println("ERROR: " + log);  
    }  

}  

