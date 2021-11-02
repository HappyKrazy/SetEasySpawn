package happy.spawn;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

import happy.commands.Command_spawn;

public class Main extends JavaPlugin{
	
	String rConfig;
	public PluginDescriptionFile info = getDescription();
	public String name = ChatColor.translateAlternateColorCodes('&', "&a["+info.getName()+"]");
	public String version = info.getVersion();
	
	@Override
	public void onEnable() {
		Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', name+"&ahas been enable"));
		Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&aBy |HK|"));
		registerCommands();
		registerConfig();
	}
	
	public void registerCommands() {
		this.getCommand("spawn").setExecutor(new Command_spawn(this));
	}
	
	public void registerConfig() {
		 File config = new File(this.getDataFolder(),"config.yml");
		 rConfig = config.getPath();
		 if (!config.exists()) {
			this.getConfig().options().copyDefaults(true);
			saveConfig();
		}
	}
}
