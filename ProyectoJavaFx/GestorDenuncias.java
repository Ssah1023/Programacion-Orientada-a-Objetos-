
package expedientedelictivo;
    import java.util.ArrayList;
    import java.util.HashMap;
    import java.util.List;
    import java.util.Map;
    import java.sql.Connection;
    import java.sql.PreparedStatement;
    import java.sql.ResultSet;
    import java.sql.SQLException;
/**
 *
 * @author betin
 */
public class GestorDenuncias {
    private Map<Integer, Expediente> expedientes = new HashMap<>();
    private int ultimoNumeroExpediente = 0;

    /**
    
    public int registrarDenuncia(Persona denunciante, Persona denunciado, Delito delito) {
        Expediente expediente = new Expediente();
        expediente.agregarDelito(delito);
        expediente.setDenunciante(denunciante);
        expediente.setDenunciado(denunciado);
        
        expedientes.put(++ultimoNumeroExpediente, expediente);
        
        return ultimoNumeroExpediente;
    }
    **/
    
    public int registrarDenuncia(Persona denunciante, Persona denunciado, Delito delito) {
        // Obtener la conexión a la base de datos
        Connection connection = ConexionBD.getConnection();
        
        try {
            // Consultar el último número de expediente almacenado en la base de datos
            String query = "SELECT MAX(numero_expediente) FROM expedientes";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            
            if (resultSet.next()) {
                ultimoNumeroExpediente = resultSet.getInt(1); // Obtener el último número de expediente
            }
            
            // Incrementar el número de expediente
            ultimoNumeroExpediente++;
            
            // Crear el expediente y asignar los datos
            Expediente expediente = new Expediente();
            expediente.agregarDelito(delito);
            expediente.setDenunciante(denunciante);
            expediente.setDenunciado(denunciado);
            
            // Agregar el expediente al mapa de expedientes
            expedientes.put(ultimoNumeroExpediente, expediente);
            
            // Guardar el expediente en la base de datos (si es necesario)
            // Aquí deberías implementar la lógica para guardar en la base de datos
            
        } catch (SQLException ex) {
            System.out.println("Error al registrar denuncia: " + ex.getMessage());
        } finally {
            // Cerrar la conexión a la base de datos
            ConexionBD.cerrarConexion();
        }
        
        return ultimoNumeroExpediente;
    }

}
