package by.htp.library.controller;

import java.util.HashMap;
import java.util.Map;

import by.htp.library.command.Command;
import by.htp.library.command.impl.AddAuthor;
import by.htp.library.command.impl.AddBook;
import by.htp.library.command.impl.AddGenre;
import by.htp.library.command.impl.AddPublishedBy;
import by.htp.library.command.impl.ChangeLocale;
import by.htp.library.command.impl.GetAllBook;
import by.htp.library.command.impl.GetAllGenre;
import by.htp.library.command.impl.GetByAuthor;
import by.htp.library.command.impl.GetByGenre;
import by.htp.library.command.impl.GetByTitle;
import by.htp.library.command.impl.Registration;
import by.htp.library.command.impl.RemoveBook;
import by.htp.library.command.impl.ShowUpdateBook;
import by.htp.library.command.impl.SignIn;
import by.htp.library.command.impl.UpdateBook;

public class CommandProvider {

	private Map<CommandName, Command> commands = new HashMap<>();
	
	public CommandProvider(){
		commands.put(CommandName.SIGNIN, new SignIn());
		commands.put(CommandName.REGISTRATION, new Registration());
		commands.put(CommandName.GETALLGENRE, new GetAllGenre());
		commands.put(CommandName.GETBYTITLE, new GetByTitle());
		commands.put(CommandName.GETBYAUTHOR, new GetByAuthor());
		commands.put(CommandName.GETBYGENRE, new GetByGenre());
		commands.put(CommandName.UPDATEBOOK, new UpdateBook());
		commands.put(CommandName.REMOVEBOOK, new RemoveBook());
		commands.put(CommandName.SHOWUPDATEBOOK, new ShowUpdateBook());
		commands.put(CommandName.ADDBOOK, new AddBook());
		commands.put(CommandName.ADDAUTHOR, new AddAuthor());
		commands.put(CommandName.ADDPUBLISHEDBY, new AddPublishedBy());
		commands.put(CommandName.ADDGENRE, new AddGenre());
		commands.put(CommandName.GETALLBOOK, new GetAllBook());
		commands.put(CommandName.CHANGELOCALE, new ChangeLocale());
	}
	
	public Command getCommand(String commandName){
		commandName = commandName.toUpperCase();
		return commands.get(CommandName.valueOf(commandName));	
	}
	
}
