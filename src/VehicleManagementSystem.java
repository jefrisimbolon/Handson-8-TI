public class VehicleManagementSystem {
    public static void main(String[] args) {
        System.out.println("=== VEHICLE RENTAL MANAGEMENT SYSTEM ===\n");

        // ===== LATIHAN 1: CREATE VEHICLE FLEET =====
        Car avanza = new Car("Toyota Avanza", 300_000, "MPV");
        Car jazz = new Car("Honda Jazz", 350_000, "Hatchback");
        Car ertiga = new Car("Suzuki Ertiga", 320_000, "MPV");

        Motorcycle beat = new Motorcycle("Honda Beat", 100_000, "matic");
        Motorcycle nmax = new Motorcycle("Yamaha NMAX", 150_000, "matic");

        Vehicle[] fleet = {avanza, jazz, ertiga, beat, nmax};
        System.out.println("Fleet created: " + fleet.length + " vehicles\n");

        // ===== LATIHAN 2: DISPLAY ALL VEHICLES =====
        System.out.println("=== ALL VEHICLES ===");
        for (Vehicle v : fleet) {
            System.out.println(v);
        }

        // ===== LATIHAN 3: RENT A VEHICLE =====
        System.out.println("\n=== RENT A VEHICLE ===");
        RentalTransaction rental1 = new RentalTransaction("Andi", avanza, 3);
        rental1.displayRentalDetails();

        // ===== LATIHAN 4: MONTHLY REVENUE =====
        System.out.println("\n=== MONTHLY REVENUE ===");
        RentalTransaction rental2 = new RentalTransaction("Budi", jazz, 5);
        RentalTransaction rental3 = new RentalTransaction("Sari", beat, 7);
        RentalTransaction rental4 = new RentalTransaction("Rina", ertiga, 4);
        RentalTransaction rental5 = new RentalTransaction("Tono", nmax, 6);

        RentalTransaction[] rentals = {rental1, rental2, rental3, rental4, rental5};

        double totalRevenue = 0;
        for (RentalTransaction r : rentals) totalRevenue += r.calculateTotalCost();
        System.out.println("Total revenue bulan ini: Rp " + String.format("%,.0f", totalRevenue));

        // ===== LATIHAN 5: MAINTENANCE =====
        System.out.println("\n=== VEHICLE MAINTENANCE ===");
        avanza.setUnderMaintenance(true);
        nmax.setUnderMaintenance(true);

        int available = 0, maintenance = 0;
        for (Vehicle v : fleet) {
            if (v.isUnderMaintenance()) maintenance++;
            else available++;
        }
        System.out.println("Available vehicles: " + available);
        System.out.println("Under maintenance: " + maintenance);

        // ===== LATIHAN 6: FIND VEHICLES =====
        System.out.println("\n=== FIND VEHICLES BY CRITERIA ===");

        int foundCars = 0, foundMotors = 0;
        for (Vehicle v : fleet) {
            if (v instanceof Car && v.getDailyRate() < 350_000)
                foundCars++;
            if (v instanceof Motorcycle && ((Motorcycle) v).getType().equalsIgnoreCase("matic"))
                foundMotors++;
        }

        System.out.println("Found " + foundCars + " Car sesuai kriteria");
        System.out.println("Found " + foundMotors + " Motor matic");

        // ===== LATIHAN 7: RENTAL REPORT =====
        System.out.println("\n=== RENTAL REPORT ===");
        int carCount = 0, motorCount = 0;
        double carRevenue = 0, motorRevenue = 0;

        for (RentalTransaction r : rentals) {
            if (r.getVehicle() instanceof Car) {
                carCount++;
                carRevenue += r.calculateTotalCost();
            } else if (r.getVehicle() instanceof Motorcycle) {
                motorCount++;
                motorRevenue += r.calculateTotalCost();
            }
        }

        System.out.println("Mobil: " + carCount + " rentals, Rp " + String.format("%,.0f", carRevenue));
        System.out.println("Motor: " + motorCount + " rentals, Rp " + String.format("%,.0f", motorRevenue));
        System.out.println("Total: Rp " + String.format("%,.0f", (carRevenue + motorRevenue)));
    }
}

/* ==============================
   CLASS Vehicle
============================== */
class Vehicle {
    private String name;
    private double dailyRate;
    private boolean underMaintenance;

    public Vehicle(String name, double dailyRate) {
        this.name = name;
        this.dailyRate = dailyRate;
        this.underMaintenance = false;
    }

    public String getName() { return name; }
    public double getDailyRate() { return dailyRate; }
    public boolean isUnderMaintenance() { return underMaintenance; }
    public void setUnderMaintenance(boolean status) { this.underMaintenance = status; }

    public String getCategory() {
        return "Generic Vehicle";
    }

    @Override
    public String toString() {
        return getCategory() + " - " + name + " | Rp " + String.format("%,.0f", dailyRate) +
                (underMaintenance ? " [Under Maintenance]" : "");
    }
}

/* ==============================
   CLASS Car
============================== */
class Car extends Vehicle {
    private String modelType;

    public Car(String name, double dailyRate, String modelType) {
        super(name, dailyRate);
        this.modelType = modelType;
    }

    public String getModelType() {
        return modelType;
    }

    @Override
    public String getCategory() {
        return "Car";
    }

    @Override
    public String toString() {
        return super.toString() + " | Tipe: " + modelType;
    }
}

/* ==============================
   CLASS Motorcycle
============================== */
class Motorcycle extends Vehicle {
    private String type; // contoh: matic, manual

    public Motorcycle(String name, double dailyRate, String type) {
        super(name, dailyRate);
        this.type = type;
    }

    public String getType() {
        return type;
    }

    @Override
    public String getCategory() {
        return "Motorcycle";
    }

    @Override
    public String toString() {
        return super.toString() + " | Jenis: " + type;
    }
}

/* ==============================
   CLASS RentalTransaction
============================== */
class RentalTransaction {
    private String customerName;
    private Vehicle vehicle;
    private int rentalDays;

    public RentalTransaction(String customerName, Vehicle vehicle, int rentalDays) {
        this.customerName = customerName;
        this.vehicle = vehicle;
        this.rentalDays = rentalDays;
    }

    public Vehicle getVehicle() { return vehicle; }

    public double calculateTotalCost() {
        return vehicle.getDailyRate() * rentalDays;
    }

    public void displayRentalDetails() {
        System.out.println("Rental untuk: " + customerName);
        System.out.println("Kendaraan: " + vehicle.getName() + " (" + vehicle.getCategory() + ")");
        System.out.println("Durasi: " + rentalDays + " hari");
        System.out.println("Biaya per hari: Rp " + String.format("%,.0f", vehicle.getDailyRate()));
        System.out.println("Total biaya: Rp " + String.format("%,.0f", calculateTotalCost()));
    }
}
