package profilo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import it.unisa.model.OperazioniModel;

public class CartaFedeltaDAO implements OperazioniModel<CartaFedeltaBean> {

 DataSource ds;
public CartaFedeltaDAO(DataSource d) {
ds=d;
}
@Override
public CartaFedeltaBean doRetriveByKey(String code) throws SQLException {
	Connection connessione= ds.getConnection();
	String query="SELECT * FROM cartafedelta WHERE codice= ?;";
	PreparedStatement ps= connessione.prepareStatement(query);
	ps.setString(1,code);
	ResultSet risultato= ps.executeQuery();
	while(risultato.next()) {
		CartaFedeltaBean app= new CartaFedeltaBean();
		app.setCodice(risultato.getString("codice"));
		app.setPunti(risultato.getInt("punti"));
		risultato.close();
		ps.close();
		connessione.close();
		return app;
	}
	risultato.close();
	ps.close();
	connessione.close();
	return null;
}

 @Override
public ArrayList<CartaFedeltaBean> doRetriveAll(String order) throws SQLException {
	Connection connessione= ds.getConnection();
	String query="SELECT * FROM cartafedelta ORDER BY ?;";
	PreparedStatement ps= connessione.prepareStatement(query);
	if(order!=null && !(order.equals("")))
	ps.setString(1, order);
	else
	ps.setString(1, "codice desc");
	ArrayList<CartaFedeltaBean> a= new ArrayList<CartaFedeltaBean>();
	ResultSet risultato= ps.executeQuery();
	while(risultato.next()) {
		CartaFedeltaBean app= new CartaFedeltaBean();
		app.setCodice(risultato.getString("codice"));
		app.setPunti(risultato.getInt("punti"));
		a.add(app);
	}
	risultato.close();
	ps.close();
	connessione.close();
	return a;
}

 @Override
public void doSave(CartaFedeltaBean item) throws SQLException {
	Connection connessione = ds.getConnection();//ottengo la connessione
	String query="INSERT INTO cartafedelta VALUES(?,?);";
	PreparedStatement ps= connessione.prepareStatement(query);
	ps.setString(1, item.getCodice());
	ps.setInt(2, item.getPunti());
	ps.executeUpdate();
	ps.close();
	connessione.close();
}

 @Override
public void doUpdate(CartaFedeltaBean item) throws SQLException {
	Connection con = ds.getConnection();
	String str = "UPDATE cartafedelta SET punti=punti+1 WHERE codice=?";
	PreparedStatement ps = con.prepareStatement(str);
	ps.setString(1,item.getCodice());
	ps.executeUpdate();
	ps.close();
	con.close();
}

 @Override
public void doDelete(CartaFedeltaBean item) throws SQLException {
// TODO Auto-generated method stub

 }
}