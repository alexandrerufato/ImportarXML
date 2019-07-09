package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import dao.interfaces.INotaDao;
import sefaz.TNFe.InfNFe.Det;
import sefaz.TNfeProc;

public class NotaDao implements INotaDao {
	
	@Override
	public String incluir(TNfeProc tNfeProc) throws Exception {
		try {
            //ABRE CONEXAO COM O BANCO
            Connection conn = new Conexao().getConexao();
            //*********************************************

            //PREPARA O SQL PARA EXECUÇÃO
            PreparedStatement ps = conn.prepareStatement("INSERT INTO nota(versao, tipo_nota, chave, serie, numero, data_emissao) VALUES (?, ?, ?, ?, ?, ?)");
            ps.setString(1, tNfeProc.getNFe().getInfNFe().getVersao());
            ps.setString(2, tNfeProc.getNFe().getInfNFe().getIde().getMod());
            ps.setString(3, tNfeProc.getProtNFe().getInfProt().getChNFe());
            ps.setString(4, tNfeProc.getNFe().getInfNFe().getIde().getSerie());
            ps.setString(5, tNfeProc.getNFe().getInfNFe().getIde().getNNF());
            ps.setString(6, tNfeProc.getNFe().getInfNFe().getIde().getDhEmi());
            ps.execute();
            conn.close();
            incluirItens(tNfeProc);
            return "ok";
        } catch (Exception erro) {
        	System.out.println(erro.getMessage());
        	throw erro;
        	
        }
	}
	
	private void incluirItens(TNfeProc tNfeProc) {
		try {
			if(tNfeProc != null) {
				Integer idNota = obterUltimoIdInserido();
				if(idNota > 0 ) {
		            Connection conn = new Conexao().getConexao();
		            for(Det item : tNfeProc.getNFe().getInfNFe().getDet()) {
		            	PreparedStatement ps = conn.prepareStatement("INSERT INTO item_nota(nota_id, codigo, nome, codigo_barra, ncm, cest, cfop, quantidade, valor_unitario, valor_total) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
		            	ps.setInt(1, idNota);
		            	ps.setString(2, item.getNItem());
		            	ps.setString(3, item.getProd().getXProd());
		            	ps.setString(4, item.getProd().getCEAN());
		            	ps.setString(5, item.getProd().getNCM());
		            	ps.setString(6, item.getProd().getCEST());
		            	ps.setString(7, item.getProd().getCFOP());
		            	ps.setString(8, item.getProd().getQCom());
		            	ps.setString(9, item.getProd().getVUnCom());
		            	ps.setString(10, item.getProd().getVProd());
		            	ps.execute();
		            }
		            conn.close();
				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public Integer obterUltimoIdInserido() throws Exception {
        ResultSet rs;
        Integer id = 0;
        Connection conn = new Conexao().getConexao();
        PreparedStatement ps = conn.prepareStatement("SELECT MAX(ID) AS ULTIMO FROM NOTA");
        rs = ps.executeQuery();
        if (rs.next()) {
        	id = (Integer) rs.getObject("ULTIMO");
        }
        conn.close();
       return id;
    }

	@Override
	public String consultar() {
		return "";
	}
}
