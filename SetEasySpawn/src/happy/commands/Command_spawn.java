package happy.commands;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import happy.spawn.Main;
import net.md_5.bungee.api.ChatColor;

public class Command_spawn implements CommandExecutor{
	
private Main plugin;
	
	public Command_spawn(Main plugin) {
		this.plugin = plugin;
	}
	
	public boolean onCommand(CommandSender sender, Command cmd,String label, String[] args) {
		
		if (!(sender instanceof Player)) {
			Bukkit.getConsoleSender().sendMessage(plugin.name+" Unable to send commands from the console");
			return false;
		}
		else {
			FileConfiguration config = plugin.getConfig();
			String preffix = "Config.preffix";
			Player player = (Player) sender;
			if (args.length > 0) {
				if (args[0].equals("setspawn")) {
					Location location = player.getLocation();
					
					String world = location.getWorld().getName();
					double cX = location.getX();
					double cY = location.getY();
					double cZ = location.getZ();
					float cPitch = location.getPitch();
					float cYaw = location.getYaw();
					
					
					String define_spawn = "Config.define_spawn";
					player.sendMessage(ChatColor.translateAlternateColorCodes('&', config.getString(preffix)+" "+config.getString(define_spawn)));
					config.set("Spawn.world", world);
					config.set("Spawn.x", cX);
					config.set("Spawn.y", cY);
					config.set("Spawn.z", cZ);
					config.set("Spawn.pitch", cPitch);
					config.set("Spawn.yaw", cYaw);			
					plugin.saveConfig();
					
					
					return true;
				}
				else if(args[0].equals("reload")) {
					String reload = "Config.reload";
					plugin.reloadConfig();
					player.sendMessage(ChatColor.translateAlternateColorCodes('&', config.getString(preffix)+" "+config.getString(reload)));
					return true;
				}
				else {
						player.sendMessage(ChatColor.translateAlternateColorCodes('&',config.getString(preffix)+" &cThe command does not exist."));
				}
			
			}
			else {
				if (config.contains("Spawn.x")) {
					String spawn = "Config.spawn";
					World world = plugin.getServer().getWorld(config.getString("Spawn.world"));
					Double x = Double.valueOf(config.getString("Spawn.x"));
					Double y = Double.valueOf(config.getString("Spawn.y"));
					Double z = Double.valueOf(config.getString("Spawn.z"));
					float pitch = Float.valueOf(config.getString("Spawn.pitch"));
					float yaw = Float.valueOf(config.getString("Spawn.	yaw"));
					
					
					Location location = new Location(world,x,y,z,pitch,yaw);
					player.teleport(location);
					player.sendMessage(ChatColor.translateAlternateColorCodes('&', config.getString(preffix)+" "+config.getString(spawn)));
					return true;
				}
				else {
					String no_spawn = "Config.no_spawn";
					player.sendMessage(ChatColor.translateAlternateColorCodes('&', config.getString(preffix)+" "+config.getString(no_spawn)));
					return true;
				}
			
			}
		}
		
		return false;
	}
}
