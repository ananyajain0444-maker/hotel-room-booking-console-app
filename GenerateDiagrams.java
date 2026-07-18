import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class GenerateDiagrams {
    // High-DPI 3x scaling factor for crystal clear, non-blurry output
    private static final int SCALE = 3;

    // Recruiter-Preferred High-Vibrancy Minimalist Color Palette (Light Mode)
    private static final Color BG_LIGHT = Color.WHITE; // Clean white base
    private static final Color BORDER_FRAME = new Color(226, 232, 240); // Soft, clean gray

    // High-Vibrancy Text & Accent Colors (Muted for readability, high-vibrancy for professional look)
    private static final Color TEXT_PRIMARY = new Color(15, 23, 42); // Deep Slate (Main text, very bold)
    private static final Color TEXT_MUTED = new Color(71, 85, 105); // Clean Slate (Muted text, sharp and legible)
    private static final Color VIBRANT_BLUE = new Color(37, 99, 235); // Modern Tech Blue (Primary Accents)
    private static final Color VIBRANT_GREEN = new Color(22, 163, 74); // Vibrant Success Green (Confirmations/Sync)

    // Design Utility Assistants
    private static void setupGraphics(Graphics2D g) {
        // Essential Quality Rendering Hints for non-blurry text
        g.scale(SCALE, SCALE); // Upscale rendering coordinates
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB); // Subpixel LCD smoothing
        g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
    }

    public static void main(String[] args) {
        try {
            // Create target directory if it doesn't exist
            File dir = new File("images");
            if (!dir.exists()) dir.mkdir();

            System.out.println("⏳ Generating ultra-crisp, vibrant portfolio assets...");

            generateMainMenuVibrant();
            generateBookingConfirmationVibrant();

            System.out.println("\n============= SUCCESS =============");
            System.out.println("✅ Pixel-perfect vibrant diagrams created inside 'images/' folder!");
        } catch (Exception e) {
            System.out.println("❌ Error generating images: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void generateMainMenuVibrant() throws Exception {
        Font consoleFont = new Font("Consolas", Font.BOLD, 15);
        int w = 680, h = 530; // Fits all 11 items comfortably

        BufferedImage menuImg = new BufferedImage(w * SCALE, h * SCALE, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = menuImg.createGraphics();
        setupGraphics(g);

        // Canvas & Frame setup
        g.setColor(BG_LIGHT);
        g.fillRect(0, 0, w, h);
        g.setColor(BORDER_FRAME);
        g.setStroke(new BasicStroke(2));
        g.drawRect(1, 1, w - 2, h - 2);

        // Terminal Prompt line (Bold Muted Text)
        g.setColor(TEXT_MUTED);
        g.setFont(consoleFont);
        g.drawString("user@developer-pc:~/hotel-app$ java main.Main", 30, 45);

        // Bold Banner Section (Vibrant Blue)
        g.setColor(VIBRANT_BLUE);
        g.drawString("=====================================================", 30, 80);
        g.drawString("          HOTEL ROOM BOOKING CONSOLE SYSTEM          ", 30, 100);
        g.drawString("=====================================================", 30, 120);

        // 11 Menu Options (Bold Primary Text)
        g.setColor(TEXT_PRIMARY);
        g.drawString(" 1. Display All Rooms Details", 30, 155);
        g.drawString(" 2. Display Available Rooms Only", 30, 185);
        g.drawString(" 3. Search Rooms by Type", 30, 215);
        g.drawString(" 4. Create New Customer Booking", 30, 245);
        g.drawString(" 5. View Specific Booking Details", 30, 275);
        g.drawString(" 6. View All System Bookings", 30, 305);
        g.drawString(" 7. Check-In Guest Process", 30, 335);
        g.drawString(" 8. Check-Out Guest & Settlement", 30, 365);
        g.drawString(" 9. Cancel Existing Reservation", 30, 395);
        g.drawString(" 10. View Complete Booking History File", 30, 425);
        g.drawString(" 11. View Hotel Analytics Summary & Exit", 30, 455);

        // Choice Prompt (Bold Vibrant Blue)
        g.setColor(VIBRANT_BLUE);
        g.drawString(" Enter your choice (1-11): 4_", 30, 495);

        g.dispose();
        ImageIO.write(menuImg, "png", new File("images/main_menu.png"));
    }

    private static void generateBookingConfirmationVibrant() throws Exception {
        Font consoleFont = new Font("Consolas", Font.BOLD, 15);
        int w = 680, h = 430;

        BufferedImage confirmImg = new BufferedImage(w * SCALE, h * SCALE, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = confirmImg.createGraphics();
        setupGraphics(g);

        // Canvas & Frame setup
        g.setColor(BG_LIGHT);
        g.fillRect(0, 0, w, h);
        g.setColor(BORDER_FRAME);
        g.setStroke(new BasicStroke(2));
        g.drawRect(1, 1, w - 2, h - 2);

        // Bold Banner Section (Vibrant Green)
        g.setColor(VIBRANT_GREEN);
        g.setFont(consoleFont);
        g.drawString(">>> BOOKING VERIFIED & SYSTEM RECORD CREATED <<<", 30, 55);

        // Core Data Section (Bold Primary Text)
        g.setColor(TEXT_PRIMARY);
        g.drawString(" Booking Identifier : BK1001", 30, 105);
        g.drawString(" Registered Guest   : Ananya Jain", 30, 135);
        g.drawString(" Room Unit Assigned : Room 101 [Single Deluxe]", 30, 165);
        g.drawString(" Selected Duration  : 2 Nights Ledger", 30, 195);
        g.drawString(" Net Total Amount   : Rs. 4400.00", 30, 225);
        g.drawString(" Current Status     : CONFIRMED", 30, 255);

        // Synchronisation Status line (Bold Muted Divider)
        g.setColor(TEXT_MUTED);
        g.drawString("-----------------------------------------------------", 30, 295);

        // Synchronisation Confirmation (Vibrant Green Checkmark and Text)
        g.setColor(VIBRANT_GREEN);
        g.drawString(" ✔ Direct Synchronization complete -> data/bookings.txt", 30, 335);

        g.dispose();
        ImageIO.write(confirmImg, "png", new File("images/booking_confirmation.png"));
    }
}