package pl.noname.stacjabenzynowa.persistance.enumconverter;

import pl.noname.stacjabenzynowa.persistance.AclRule;

public class EnumAclRuleAction extends PersistentEnumType<AclRule.Action>
{
	@Override
	public Class<AclRule.Action> returnedClass() {
		return AclRule.Action.class;
	}
}