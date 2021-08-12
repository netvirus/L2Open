package quests._286_FabulousFeathers;

import l2open.extensions.scripts.ScriptFile;
import l2open.gameserver.model.instances.L2NpcInstance;
import l2open.gameserver.model.quest.Quest;
import l2open.gameserver.model.quest.QuestState;
import l2open.util.Rnd;

public class _286_FabulousFeathers extends Quest implements ScriptFile
{
	//NPCs
	private static int ERINU = 32164;
	//Mobs
	private static int Shady_Muertos_Captain = 22251;
	private static int Shady_Muertos_Warrior = 22253;
	private static int Shady_Muertos_Archer = 22254;
	private static int Shady_Muertos_Commander = 22255;
	private static int Shady_Muertos_Wizard = 22256;
	//Quest Items
	private static int Commanders_Feather = 9746;
	//Chances
	private static int Commanders_Feather_Chance = 66;

	public _286_FabulousFeathers()
	{
		super(false);
		addStartNpc(ERINU);
		addKillId(Shady_Muertos_Captain);
		addKillId(Shady_Muertos_Warrior);
		addKillId(Shady_Muertos_Archer);
		addKillId(Shady_Muertos_Commander);
		addKillId(Shady_Muertos_Wizard);
		addQuestItem(Commanders_Feather);
	}

	@Override
	public String onEvent(String event, QuestState st, L2NpcInstance npc)
	{
		int _state = st.getState();
		if(event.equalsIgnoreCase("trader_erinu_q0286_0103.htm") && _state == CREATED)
		{
			st.setState(STARTED);
			st.set("cond", "1");
			st.playSound(SOUND_ACCEPT);
		}
		else if(event.equalsIgnoreCase("trader_erinu_q0286_0201.htm") && _state == STARTED)
		{
			st.takeItems(Commanders_Feather, -1);
			st.giveItems(ADENA_ID, 4160);
			st.playSound(SOUND_FINISH);
			st.exitCurrentQuest(true);
		}
		return event;
	}

	@Override
	public String onTalk(L2NpcInstance npc, QuestState st)
	{
		String htmltext = "noquest";
		if(npc.getNpcId() != ERINU)
			return htmltext;
		int _state = st.getState();

		if(_state == CREATED)
		{
			if(st.getPlayer().getLevel() >= 17)
			{
				htmltext = "trader_erinu_q0286_0101.htm";
				st.set("cond", "0");
			}
			else
			{
				htmltext = "trader_erinu_q0286_0102.htm";
				st.exitCurrentQuest(true);
			}
		}
		else if(_state == STARTED)
			htmltext = st.getQuestItemsCount(Commanders_Feather) >= 80 ? "trader_erinu_q0286_0105.htm" : "trader_erinu_q0286_0106.htm";

		return htmltext;
	}

	@Override
	public String onKill(L2NpcInstance npc, QuestState qs)
	{
		if(qs.getState() != STARTED)
			return null;

		long Commanders_Feather_count = qs.getQuestItemsCount(Commanders_Feather);
		if(Commanders_Feather_count < 80 && Rnd.chance(Commanders_Feather_Chance))
		{
			qs.giveItems(Commanders_Feather, 1);
			if(Commanders_Feather_count == 79)
			{
				qs.set("cond", "2");
				qs.playSound(SOUND_MIDDLE);
			}
			else
				qs.playSound(SOUND_ITEMGET);
		}
		return null;
	}

	public void onLoad()
	{}

	public void onReload()
	{}

	public void onShutdown()
	{}
}