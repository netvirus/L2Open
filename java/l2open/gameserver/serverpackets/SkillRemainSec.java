package l2open.gameserver.serverpackets;

public class SkillRemainSec extends L2GameServerPacket
{
	@Override
	protected void writeImpl()
	{
		writeC(0xD8);
		//TODO ddddddd
	}
}