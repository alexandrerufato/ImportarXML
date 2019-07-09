package dao.interfaces;

import sefaz.TNfeProc;

public interface INotaDao {
	public String consultar();

	public String incluir(TNfeProc tNfeProc) throws Exception;
}
