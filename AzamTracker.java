import java.util.*;
import java.io.*;
import java.net.*;
import java.util.regex.*;
import org.json.JSONObject;

public class AzamTracker {
    // Color codes
    public static final String Bl = "\033[30m";
    public static final String Re = "\033[1;31m";
    public static final String Gr = "\033[1;32m";
    public static final String Ye = "\033[1;33m";
    public static final String Blu = "\033[1;34m";
    public static final String Mage = "\033[1;35m";
    public static final String Cy = "\033[1;36m";
    public static final String Wh = "\033[1;37m";
    
    private static Scanner scanner = new Scanner(System.in);
    
    public static void main(String[] args) {
        try {
            mainMenu();
        } catch (Exception e) {
            System.out.println(Re + "Error: " + e.getMessage());
        }
    }
    
    public static void mainMenu() {
        clearScreen();
        printBanner();
        
        System.out.println(Wh + "\n\n[1] IP Tracker");
        System.out.println("[2] Show Your IP");
        System.out.println("[3] Phone Number Tracker");
        System.out.println("[4] Username Tracker");
        System.out.println("[0] Exit");
        
        try {
            System.out.print(Wh + "\n[ + ] " + Gr + "Select Option : " + Wh);
            int opt = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            
            switch(opt) {
                case 1:
                    runBanner();
                    IP_Track();
                    break;
                case 2:
                    runBanner();
                    showIP();
                    break;
                case 3:
                    runBanner();
                    phoneGW();
                    break;
                case 4:
                    runBanner();
                    TrackLu();
                    break;
                case 0:
                    System.out.println(Wh + "[ " + Re + "! " + Wh + "] " + Re + "Exit");
                    System.exit(0);
                    break;
                default:
                    System.out.println(Re + "Invalid option!");
                    pause();
                    mainMenu();
            }
        } catch (Exception e) {
            System.out.println(Re + "Please input a number");
            pause();
            mainMenu();
        }
    }
    
    public static void IP_Track() {
        try {
            System.out.print(Wh + "\n Enter IP target : " + Gr);
            String ip = scanner.nextLine();
            System.out.println();
            
            System.out.println(Wh + "============= " + Gr + "SHOW INFORMATION IP ADDRESS " + Wh + "=============");
            
            URL url = new URL("http://ipwho.is/" + ip);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;
            
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();
            
            JSONObject ipData = new JSONObject(response.toString());
            
            System.out.println(Wh + "\n IP target       :" + Gr + ip);
            System.out.println(Wh + " Type IP         :" + Gr + ipData.getString("type"));
            System.out.println(Wh + " Country         :" + Gr + ipData.getString("country"));
            System.out.println(Wh + " Country Code    :" + Gr + ipData.getString("country_code"));
            System.out.println(Wh + " City            :" + Gr + ipData.getString("city"));
            System.out.println(Wh + " Continent       :" + Gr + ipData.getString("continent"));
            System.out.println(Wh + " Continent Code  :" + Gr + ipData.getString("continent_code"));
            System.out.println(Wh + " Region          :" + Gr + ipData.getString("region"));
            System.out.println(Wh + " Region Code     :" + Gr + ipData.getString("region_code"));
            System.out.println(Wh + " Latitude        :" + Gr + ipData.getDouble("latitude"));
            System.out.println(Wh + " Longitude       :" + Gr + ipData.getDouble("longitude"));
            
            double lat = ipData.getDouble("latitude");
            double lon = ipData.getDouble("longitude");
            System.out.println(Wh + " Maps            :" + Gr + "https://www.google.com/maps/@" + lat + "," + lon + ",8z");
            
            System.out.println(Wh + " EU              :" + Gr + ipData.getBoolean("is_eu"));
            System.out.println(Wh + " Postal          :" + Gr + ipData.getString("postal"));
            System.out.println(Wh + " Calling Code    :" + Gr + ipData.getString("calling_code"));
            System.out.println(Wh + " Capital         :" + Gr + ipData.getString("capital"));
            System.out.println(Wh + " Borders         :" + Gr + ipData.getString("borders"));
            
            JSONObject flag = ipData.getJSONObject("flag");
            System.out.println(Wh + " Country Flag    :" + Gr + flag.getString("emoji"));
            
            JSONObject connection = ipData.getJSONObject("connection");
            System.out.println(Wh + " ASN             :" + Gr + connection.getString("asn"));
            System.out.println(Wh + " ORG             :" + Gr + connection.getString("org"));
            System.out.println(Wh + " ISP             :" + Gr + connection.getString("isp"));
            System.out.println(Wh + " Domain          :" + Gr + connection.getString("domain"));
            
            JSONObject timezone = ipData.getJSONObject("timezone");
            System.out.println(Wh + " ID              :" + Gr + timezone.getString("id"));
            System.out.println(Wh + " ABBR            :" + Gr + timezone.getString("abbr"));
            System.out.println(Wh + " DST             :" + Gr + timezone.getBoolean("is_dst"));
            System.out.println(Wh + " Offset          :" + Gr + timezone.getString("offset"));
            System.out.println(Wh + " UTC             :" + Gr + timezone.getString("utc"));
            System.out.println(Wh + " Current Time    :" + Gr + timezone.getString("current_time"));
            
        } catch (Exception e) {
            System.out.println(Re + "Error: " + e.getMessage());
        }
        
        pause();
        mainMenu();
    }
    
    public static void phoneGW() {
        try {
            System.out.print(Wh + "\n Enter phone number target " + Gr + "Ex [+6281xxxxxxxxx] " + Wh + ": " + Gr);
            String userPhone = scanner.nextLine();
            
            // Basic phone number validation (simplified)
            if (!userPhone.startsWith("+")) {
                System.out.println(Re + "Phone number must start with country code (e.g., +62)");
                pause();
                mainMenu();
                return;
            }
            
            System.out.println(Wh + "\n ========== " + Gr + "SHOW INFORMATION PHONE NUMBERS " + Wh + "==========");
            
            // This is a simplified version as Java doesn't have a direct equivalent to phonenumbers library
            String countryCode = userPhone.substring(1, 3);
            String operator = detectOperator(userPhone);
            
            System.out.println(Wh + "Country code         :" + Gr + countryCode);
            System.out.println(Wh + "International format :" + Gr + userPhone);
            System.out.println(Wh + "Operator             :" + Gr + operator);
            System.out.println(Wh + "Type                 :" + Gr + "This is a mobile number");
            
        } catch (Exception e) {
            System.out.println(Re + "Error: " + e.getMessage());
        }
        
        pause();
        mainMenu();
    }
    
    private static String detectOperator(String phoneNumber) {
        // Simplified operator detection based on Indonesian prefixes
        if (phoneNumber.contains("081") || phoneNumber.contains("082") || phoneNumber.contains("083")) 
            return "Telkomsel";
        if (phoneNumber.contains("085") || phoneNumber.contains("0817") || phoneNumber.contains("0818")) 
            return "Indosat";
        if (phoneNumber.contains("087") || phoneNumber.contains("088")) 
            return "XL";
        if (phoneNumber.contains("089")) 
            return "Tri";
        return "Unknown Operator";
    }
    
    public static void TrackLu() {
        try {
            System.out.print(Wh + "\n Enter Username : " + Gr);
            String username = scanner.nextLine();
            
            Map<String, String> results = new LinkedHashMap<>();
            String[][] socialMedia = {
                {"Facebook", "https://www.facebook.com/{}"},
                {"Twitter", "https://www.twitter.com/{}"},
                {"Instagram", "https://www.instagram.com/{}"},
                {"LinkedIn", "https://www.linkedin.com/in/{}"},
                {"GitHub", "https://www.github.com/{}"},
                {"Pinterest", "https://www.pinterest.com/{}"},
                {"Tumblr", "https://www.tumblr.com/{}"},
                {"Youtube", "https://www.youtube.com/{}"},
                {"SoundCloud", "https://soundcloud.com/{}"},
                {"Snapchat", "https://www.snapchat.com/add/{}"},
                {"TikTok", "https://www.tiktok.com/@{}"},
                {"Behance", "https://www.behance.net/{}"},
                {"Medium", "https://www.medium.com/@{}"},
                {"Quora", "https://www.quora.com/profile/{}"},
                {"Flickr", "https://www.flickr.com/people/{}"},
                {"Twitch", "https://www.twitch.tv/{}"},
                {"Dribbble", "https://www.dribbble.com/{}"},
                {"Telegram", "https://www.telegram.me/{}"}
            };
            
            for (String[] site : socialMedia) {
                String siteName = site[0];
                String url = site[1].replace("{}", username);
                
                try {
                    HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
                    connection.setRequestMethod("GET");
                    connection.setConnectTimeout(5000);
                    connection.setReadTimeout(5000);
                    
                    int responseCode = connection.getResponseCode();
                    if (responseCode == 200) {
                        results.put(siteName, url);
                    } else {
                        results.put(siteName, Ye + "Username not found " + Ye + "!");
                    }
                } catch (Exception e) {
                    results.put(siteName, Ye + "Error checking " + siteName);
                }
            }
            
            System.out.println(Wh + "\n ========== " + Gr + "SHOW INFORMATION USERNAME " + Wh + "==========");
            System.out.println();
            
            for (Map.Entry<String, String> entry : results.entrySet()) {
                System.out.println(Wh + "[ " + Gr + "+ " + Wh + "] " + entry.getKey() + " : " + Gr + entry.getValue());
            }
            
        } catch (Exception e) {
            System.out.println(Re + "Error: " + e.getMessage());
        }
        
        pause();
        mainMenu();
    }
    
    public static void showIP() {
        try {
            URL url = new URL("https://api.ipify.org");
            BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
            String showIP = reader.readLine();
            reader.close();
            
            System.out.println(Wh + "\n ========== " + Gr + "SHOW INFORMATION YOUR IP " + Wh + "==========");
            System.out.println(Wh + "\n [" + Gr + " + " + Wh + "] Your IP Address : " + Gr + showIP);
            System.out.println(Wh + "\n ==============================================");
            
        } catch (Exception e) {
            System.out.println(Re + "Error: " + e.getMessage());
        }
        
        pause();
        mainMenu();
    }
    
    public static void clearScreen() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (Exception e) {
            System.out.println(Re + "Error clearing screen: " + e.getMessage());
        }
    }
    
    public static void runBanner() {
        clearScreen();
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {}
        
        System.out.println(Wh +
            "⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡿⠋⠁⠀⠀⠈⠉⠙⠻⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿\n" +
            "⣿⣿⣿⣿⣿⣿⣿⣿⣿⡟⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠻⣿⣿⣿⣿⣿⣿⣿⣿⣿\n" +
            "⣿⣿⣿⣿⣿⣿⣿⣿⡟⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⢻⣿⣿⣿⣿⣿⣿⣿\n" +
            "⣿⣿⣿⣿⣿⣿⣿⡟⠀⠀⠀⠀⠀⢀⣠⣤⣤⣤⣤⣄⠀⠀⠀⠹⣿⣿⣿⣿⣿⣿\n" +
            "⣿⣿⣿⣿⣿⣿⣿⠁⠀⠀⠀⠀⠾⣿⣿⣿⣿⠿⠛⠉⠀⠀⠀⠀⠘⣿⣿⣿⣿⣿\n" +
            "⣿⣿⣿⣿⣿⣿⡏⠀⠀⠀⣤⣶⣤⣉⣿⣿⡯⣀⣴⣿⡗⠀⠀⠀⠀⣿⣿⣿⣿⣿\n" +
            "⣿⣿⣿⣿⣿⣿⡇⠀⠀⠀⡈⠀⠀⠉⣿⣿⣶⡉⠀⠀⣀⡀⠀⠀⠀⢻⣿⣿⣿⣿\n" +
            "⣿⣿⣿⣿⣿⣿⡇⠀⠀⠸⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠇⠀⠀⠀⢸⣿⣿⣿⣿\n" +
            "⣿⣿⣿⣿⣿⣿⣿⠀⠀⠀⠉⢉⣽⣿⠿⣿⡿⢻⣯⡍⢁⠄⠀⠀⠀⣸⣿⣿⣿⣿\n" +
            "⣿⣿⣿⣿⣿⣿⣿⡄⠀⠀⠐⡀⢉⠉⠀⠠⠀⢉⣉⠀⡜⠀⠀⠀⠀⣿⣿⣿⣿⣿\n" +
            "⣿⣿⣿⣿⣿⣿⠿⠁⠀⠀⠀⠘⣤⣭⣟⠛⠛⣉⣁⡜⠀⠀⠀⠀⠀⠛⠿⣿⣿⣿\n" +
            "⡿⠟⠛⠉⠉⠀⠀⠀⠀⠀⠀⠀⠈⢻⣿⡀⠀⣿⠏⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠉\n" +
            "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠉⠁⠀⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
            Wh + "----------------------------------------------------------------------\n" +
            Wh + "| " + Gr + "TOOLS - TRACKER - IP ADDRESS " + Wh + "|\n" +
            Wh + "|       " + Gr + "@CODE BY @jawir666      " + Wh + "|\n" +
            Wh + "----------------------------------------------------------------------\n");
        
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {}
    }
    
    public static void printBanner() {
        System.out.println(Wh +
            "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⡗⠦⣄⣠⠤⡦⢤⣞⣹⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
            "⠤⣀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣻⣦⠞⣻⠀⢱⣄⡈⢿⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡠\n" +
            "⠀⠀⠉⠒⢤⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡇⢰⣿⣧⣀⣿⠿⡎⠙⣦⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠀\n" +
            "⡀⠀⠀⠀⠀⠈⠑⠢⢄⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣷⣏⢈⠻⠛⠙⢄⢹⣶⣿⡆⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣀⠴⠂⠁⠀⠀⠀⠀\n" +
            "⠙⢄⠀⠀⠀⠀⠀⠀⠀⠈⠢⡀⠀⠀⠀⠀⠀⠀⠀⢸⣿⢿⣻⠷⣶⠶⠊⣇⣼⣁⡀⠀⠀⠀⠀⠀⠀⠀⠀⣠⠶⠋⠀⠀⠀⠀⠀⠀⠀⠈\n" +
            "⠀⠀⠑⢤⣀⠀⠀⣠⠞⢆⠀⠈⠲⢄⠀⠀⠀⢀⡴⠿⠟⠛⠿⢷⠀⠾⠛⠛⠛⢲⠽⡄⠀⠀⠀⠀⢀⠴⠋⠀⢰⣆⠀⠀⠀⠀⠀⡀⠀⠀\n" +
            "⠀⠀⠀⠀⠀⠈⣇⠀⠀⠀⠀⠀⠀⠀⠑⢆⣠⠏⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠉⠙⠆⢀⡠⠊⠁⠀⠀⠀⠀⠙⠓⢦⡴⠒⠉⠀⠀⠀\n" +
            "⠀⠀⠀⠀⠀⠀⠘⡄⠀⠀⠀⠀⠀⠀⠀⠀⠙⢦⡀⠀⠀⠀⢀⠴⠀⠢⣀⠀⠀⠀⠀⣠⠞⠁⠀⠀⠀⠀⠀⠀⠀⠀⢀⠎⠀⠀⠀⠀⠀⠀\n" +
            "⠀⠀⠀⠀⠀⠀⠀⣱⣤⡀⠀⠀⠀⠀⠀⠀⠀⠀⠑⢤⠒⠋⠁⠀⠀⠀⠀⠉⠙⣲⠞⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⠎⠀⠀⠀⠀⠀⠀⠀\n" +
            "⠀⠀⠀⠀⠀⠀⠀⢻⣿⡗⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠳⣄⣀⠤⠤⠄⣀⣠⠞⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⠎⠂⠀⠀⠀⠀⠀⠀⠀\n" +
            "⠀⠀⠀⠀⠀⠀⠀⠀⠻⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠒⠬⡅⠀⡇⠀⠀⠇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⠞⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
            "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠘⡄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢇⢰⣿⠀⡄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⠎⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
            "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠸⡄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠘⣬⣿⣾⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣠⠋⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
            "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠙⣄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⡟⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⡴⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
            "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠦⡀⠀⠀⠀⠀⠀⠀⢀⣠⠶⠿⣄⠀⠀⠀⠀⠀⠀⠀⠀⣠⣾⣦⡄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
            "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠉⠒⠤⠤⠤⢤⡴⠎⠀⠀⠀⠀⠱⣦⣀⣀⡀⠤⠔⠚⢿⣼⣯⣿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
            "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣼⠁⠀⠀⠀⠀⠀⠀⢸⡆⠀⠀⠀⠀⠀⠀⢉⣉⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n\n" +
            "              " + Wh + "[ + ]  C O D E   B Y  A Z A M T C O D E R [ + ]");
    }
    
    public static void pause() {
        System.out.print(Wh + "\n[ " + Gr + "+ " + Wh + "] " + Gr + "Press enter to continue");
        try {
            scanner.nextLine();
        } catch (Exception e) {}
    }
                }
