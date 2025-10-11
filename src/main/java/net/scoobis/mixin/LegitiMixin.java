package net.scoobis.mixin;

import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.scoobis.LegitiPrefix;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(ClientPlayNetworkHandler.class)
public class LegitiMixin {
	@ModifyVariable(method = "sendChatMessage(Ljava/lang/String;)V", at = @At("HEAD"), ordinal = 0)
	private String content(String content) {
		if (LegitiPrefix.CONFIG.get().LegitiEnabled) {
			List<String> whitelist = new ArrayList<>();
			if (LegitiPrefix.CONFIG.get().LegitiPrefixNouns) whitelist.addAll(Set.of("NOUN", "PROPN"));
			if (LegitiPrefix.CONFIG.get().LegitiPrefixVerbs) whitelist.addAll(Set.of("VERB", "AUX"));
			if (LegitiPrefix.CONFIG.get().LegitiPrefixAdjectives) whitelist.addAll(Set.of("ADJ"));
			if (LegitiPrefix.CONFIG.get().LegitiPrefixAdverbs) whitelist.addAll(Set.of("ADV"));
			if (LegitiPrefix.CONFIG.get().LegitiPrefixDeterminers) whitelist.addAll(Set.of("DET"));
			if (LegitiPrefix.CONFIG.get().LegitiPrefixPronouns) whitelist.addAll(Set.of("PRON"));
			if (LegitiPrefix.CONFIG.get().LegitiPrefixPrepositions) whitelist.addAll(Set.of("ADP"));
			if (LegitiPrefix.CONFIG.get().LegitiPrefixParticles) whitelist.addAll(Set.of("PART"));
			if (LegitiPrefix.CONFIG.get().LegitiPrefixConjunctions) whitelist.addAll(Set.of("CCONJ", "SCONJ"));
			if (LegitiPrefix.CONFIG.get().LegitiPrefixModals) whitelist.addAll(Set.of("MD"));
			if (LegitiPrefix.CONFIG.get().LegitiPrefixMisc) whitelist.addAll(Set.of("INTJ"));

			List<String> blacklist = Arrays.asList(LegitiPrefix.CONFIG.get().LegitiCustomBlacklist);

			String[] tokens = LegitiPrefix.TOKENIZER.tokenize(content);
			if (tokens.length == 0) return content;
			String[] tags = LegitiPrefix.TAGGER.tag(tokens);
			String outString = "";
			String prefix = LegitiPrefix.CONFIG.get().LegitiPrefix.toLowerCase();
			for (int i = 0; i < tokens.length; i++) {
				boolean exception = blacklist.contains(tokens[i]) || tokens[i].toLowerCase().startsWith("legit");
				if (i > 0) {
					exception = exception || tokens[i - 1].toLowerCase().equals("an");
				}

				if ((exception || !(whitelist.contains(tags[i]))) && !LegitiPrefix.CONFIG.get().LegitiForceAllTypes) {
					if (i == 0) {
						outString = outString + tokens[i];
					} else {
						outString = outString + " " + tokens[i];
					}
				} else if (LegitiPrefix.CONFIG.get().LegitiPrefix == "") outString = outString + Character.toUpperCase(tokens[i].charAt(0)) + tokens[i].substring(1).toLowerCase();
				else if (LegitiPrefix.CONFIG.get().LegitiPascalCase) {
					if (i == 0) {
						outString = outString + Character.toUpperCase(prefix.charAt(0)) + prefix.substring(1).toLowerCase() + Character.toUpperCase(tokens[i].charAt(0)) + tokens[i].substring(1).toLowerCase();
					} else {
						outString = outString + " " + Character.toUpperCase(prefix.charAt(0)) + prefix.substring(1).toLowerCase() + Character.toUpperCase(tokens[i].charAt(0)) + tokens[i].substring(1).toLowerCase();
					}
				} else {
					if (i == 0) {
						outString = outString + LegitiPrefix.CONFIG.get().LegitiPrefix.toLowerCase() + tokens[i].toLowerCase();
					} else {
						outString = outString + " " + LegitiPrefix.CONFIG.get().LegitiPrefix.toLowerCase() + tokens[i].toLowerCase();
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