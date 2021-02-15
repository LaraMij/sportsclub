package a11847329;
import java.math.BigDecimal;

public enum Sports {

	ARCHERY, BASKETBALL, CLIMBING {
		@Override
		public BigDecimal getFeeFactor() {
			return new BigDecimal("1.2");
		}
	},
	DIVING {
		@Override
		public BigDecimal getFeeFactor() {
			return new BigDecimal("1.8");
		}
	},
	FOOTBALL, GOLF {
		@Override
		public BigDecimal getFeeFactor() {
			return new BigDecimal("2.1");
		}
	},
	HANDBALL, HOCKEY, MOUNTAINBIKING, PARKOUR;

	public BigDecimal getFeeFactor() {
		return new BigDecimal("1.0");
	}

	public BigDecimal getFee(BigDecimal feePerSport) {
		return feePerSport.multiply(getFeeFactor());
	}

}


