package fr.sii.survival.mock.ext;

import fr.sii.survival.core.ext.EnemyExtension;
import fr.sii.survival.core.ext.GameContext;
import fr.sii.survival.core.ext.annotation.Developer;

@Developer(value="abaudet", name="Aurélien Baudet")
public class UnloadableEnemyExtension implements EnemyExtension {
	public UnloadableEnemyExtension(String param) {
		
	}

	@Override
	public void init() {
		
	}

	@Override
	public void run(GameContext context) {
		// TODO Auto-generated method stub
		
	}
}