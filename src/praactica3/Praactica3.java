package praactica3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.HashMap;
import java.util.Map;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

public class Praactica3 {

    public static Connection conectar() {
        Connection con = null;
        try {
            String url = "jdbc:mysql://localhost:3306/tienda?user=root&&password=mysqladmin";
            con = DriverManager.getConnection(url);
            if (con != null) {
                System.out.println("conexion correcta");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return con;
    }

    public static void main(String[] args) {
        try {
            JasperReport jr = (JasperReport) JRLoader.loadObject(Praactica3.class.getResource("./reporte.jasper"));
            Map parametros = new HashMap<String, Object>();
            parametros.put("PARAMETRO_NOMBRE", "HOLA_MUNDO");
            parametros.put("PARAMETRO_PRECIO", 1000);
            parametros.put("PARAMETRO_PRECIO2", 3000);
            JasperPrint jp = JasperFillManager.fillReport(jr, parametros, conectar());
            JasperViewer jv = new JasperViewer(jp);
            jv.show();
        } catch (Exception e) {
            System.err.print(e);
        }
    }
}
