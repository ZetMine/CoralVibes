import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import net.zetmine.coralvibes.utils.Utils;
import org.bukkit.entity.Player;

public class Afk implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Utils utils = new Utils();
        // Vérifie si la commande est exécutée par un joueur
        if (!(sender instanceof Player)) {
            sender.sendMessage("Cette commande ne peut être utilisée que par un joueur.");
            return true;
        }

        Player player = (Player) sender;

        // Votre logique pour gérer la commande /afk ici
        player.sendMessage(utils.cvPrefix + ChatColor.GREEN +"Vous êtes maintenant AFK.");

        return true;
    }
}