package l2open.gameserver.serverpackets;

public class ExIsCharNameCreatable extends L2GameServerPacket
{	
	public static final int REASON_CREATION_OK = -1;
	public static final int REASON_CREATION_FAILED = 0x00;
	public static final int REASON_TOO_MANY_CHARACTERS = 0x01;
	public static final int REASON_NAME_ALREADY_EXISTS = 0x02;
	public static final int REASON_16_ENG_CHARS = 0x03;
	public static final int REASON_INCORRECT_NAME = 0x04;
	public static final int REASON_CHARS_CANT_CREATED_FROM_SERVER = 0x05;
	public static final int REASON_UNABLE_CREATE_REASON_TOO_CHAR = 0x06;
	
	private final int _code;
	
	public ExIsCharNameCreatable(int errorCode)
	{
		_code = errorCode;
	}
	
	@Override
	protected void writeImpl()
	{
		writeC(EXTENDED_PACKET);
		writeH(0x110);
		writeD(_code);
	}
	
	@Override
	public String getType()
	{
		return "[S] FE:110 ExIsCharNameCreatable";
	}
}