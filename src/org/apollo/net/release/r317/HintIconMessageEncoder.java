package org.apollo.net.release.r317;

import org.apollo.game.message.impl.HintIconMessage;
import org.apollo.net.codec.game.DataType;
import org.apollo.net.codec.game.GamePacket;
import org.apollo.net.codec.game.GamePacketBuilder;
import org.apollo.net.release.MessageEncoder;

/**
 * A {@link MessageEncoder} for the {@link HintIconMessage}.
 *
 * @author Major
 */
public final class HintIconMessageEncoder extends MessageEncoder<HintIconMessage> {

	@Override
	public GamePacket encode(HintIconMessage message) {
		GamePacketBuilder builder = new GamePacketBuilder(254);
		int type = message.getType();
		builder.put(DataType.BYTE, type);

		switch (type) {
			case HintIconMessage.NPC_TYPE:
			case HintIconMessage.PLAYER_TYPE:
				builder.put(DataType.SHORT, message.getIndex().get());
				break;
			default:
				throw new IllegalStateException("Unsupported hint icon type " + type + ".");
		}

		System.out.println("type=" + type + ", index=" + message.getIndex().get());

		return builder.toGamePacket();
	}

}