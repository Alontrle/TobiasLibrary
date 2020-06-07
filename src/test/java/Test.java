import com.tobiassteely.tobiasapi.TobiasAPI;
import com.tobiassteely.tobiasapi.TobiasBuilder;

public class Test {

    public static void main(String[] args) {
        TobiasAPI tobiasAPI = new TobiasBuilder().loadConfigManager("").loadCommandManager("Testing System", true).build();
    }

}
