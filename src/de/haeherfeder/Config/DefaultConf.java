package de.haeherfeder.Config;

public enum DefaultConf {
	debug(PropType.bool,true,"debug"),
	port(PropType.inte,4210,"port"),
	maxPlayers(PropType.inte,20,"maxPlayers"),
	maxAccounts(PropType.inte,20,"maxAccounts"),
	debugType(PropType.string,"file","debugType"),
	debugDir(PropType.string,"MrX/debug/","debugDir");
	private final PropType cl;
	private final Object ob;
	private final String name;
	DefaultConf(PropType cl,Object ob,String name){this.cl = cl;this.ob = ob;this.name = name;}
	Object getob(){
		return ob;
	}
	PropType getPropType() {
		return cl;
	}
	String getName() {
		return name;
	}
}
