import java.util.ArrayList;
import java.util.Scanner;

public class Product {
    
    private String productId;
    private String name;
    private String manufacturingDate;
    private String expirationDate;
    private int quantity;

    public Product() {
    }

    public Product(String productId, String name, String manufacturingDate, String expirationDate, int quantity) {
        this.productId = productId;
        this.name = name;
        this.manufacturingDate = manufacturingDate;
        this.expirationDate = expirationDate;
        this.quantity = quantity;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getManufacturingDate() {
        return manufacturingDate;
    }

    public void setManufacturingDate(String manufacturingDate) {
        this.manufacturingDate = manufacturingDate;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    public void input () {
        Scanner sc = new Scanner(System.in);
        Boolean inputInvalid;
        
        do {
            inputInvalid = false;
            try {
                System.out.print("Enter product code: ");
                productId = sc.nextLine();
                
                System.out.print("Enter product name: ");
                name = sc.nextLine();

                System.out.print("Enter manufacturing date (dd/mm/yyyy): ");
                manufacturingDate = sc.nextLine();

                System.out.print("Enter expiration date (dd/mm/yyyy): ");
                expirationDate = sc.nextLine();

                System.out.print("Enter quantity: ");
                quantity = Integer.parseInt(sc.nextLine());
                
            } catch (Exception e) {
                inputInvalid = true;
                System.err.println(e.getMessage());
            }
        } while (inputInvalid);
    }
}
