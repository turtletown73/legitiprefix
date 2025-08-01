package net.scoobis.mixin;

import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.scoobis.LegitiPrefix;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(ClientPlayNetworkHandler.class)
public class LegitiMixin {
	@ModifyVariable(method = "sendChatMessage(Ljava/lang/String;)V", at = @At("HEAD"), ordinal = 0)
	private String content(String content) {
		if (LegitiPrefix.CONFIG.get().LegitiEnabled) {
			String[] tokens = content.split(" ");
			String[] tags = LegitiPrefix.TAGGER.tag(tokens);
			String outString = "";
			for (int i = 0; i < tokens.length; i++) {
				boolean exception = tokens[i].toLowerCase().equals("i") || tokens[i].toLowerCase().contains("legit");
				if (i > 0) {
					exception = exception || tokens[i - 1].toLowerCase().equals("an");
				}
				if (exception) {
					if (i == 0) {
						outString = outString + tokens[i];
					} else {
						outString = outString + " " + tokens[i];
					}
				} else if (!(tags[i].equals("NOUN") || tags[i].equals("PROPN") || tags[i].equals("VERB") || tags[i].equals("ADJ"))) {
					if (i == 0) {
						outString = outString + tokens[i];
					} else {
						outString = outString + " " + tokens[i];
					}
				} else if (LegitiPrefix.CONFIG.get().LegitiPascalCase) {
					if (i == 0) {
						outString = outString + "Legiti" + Character.toUpperCase(tokens[i].charAt(0)) + tokens[i].substring(1).toLowerCase();
					} else {
						outString = outString + " Legiti" + Character.toUpperCase(tokens[i].charAt(0)) + tokens[i].substring(1).toLowerCase();
					}
				} else {
					if (i == 0) {
						outString = outString + "legiti" + tokens[i].toLowerCase();
					} else {
						outString = outString + " legiti" + tokens[i].toLowerCase();
					}
				}
			}
			if (outString.length() < 256) {
				content = outString;
			}
		}

		return content;
	}
}