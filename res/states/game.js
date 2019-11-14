var init = function(gstate) {
	var c = Java.type('me.gamestate.GameState')
	c = gstate
	gstate.setState('game')
}