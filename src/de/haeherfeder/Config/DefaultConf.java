package de.haeherfeder.Config;

public enum DefaultConf {
	debug(PropType.bool,true,"debug"),
	debugType(PropType.string,"file","debugType"),
	debugDir(PropType.string,"debug/","debugDir");
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
