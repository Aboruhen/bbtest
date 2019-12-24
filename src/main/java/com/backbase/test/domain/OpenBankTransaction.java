package com.backbase.test.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

/**
 * OpenBank transaction entity
 * Holder for sub entities
 */
@Setter
@NoArgsConstructor
public class OpenBankTransaction {

    private String id;
    private ThisAccount this_account;
    private OtherAccount other_account;
    private Details details;

    @Setter
    @NoArgsConstructor
    public static class ThisAccount {
        private String id;
    }

    @Setter
    @NoArgsConstructor
    public static class OtherAccount {
        private String number;
        private Holder holder;
        private Metadata metadata;

        @Setter
        @NoArgsConstructor
        public static class Holder {
            private String name;
        }

        @Setter
        @NoArgsConstructor
        public static class Metadata {
            private String image_URL;
        }

    }

    @Setter
    @NoArgsConstructor
    public static class Details {
        private String type;
        private String description;
        private Value value;

        @Setter
        @NoArgsConstructor
        public static class Value {
            private String amount;
            private String currency;
        }

    }

    public String getId() {
        return id;
    }

    public String getDetailsType() {
        return Objects.isNull(details) ? null : details.type;
    }

    public String getDetailsDescription() {
        return Objects.isNull(details) ? null : details.description;
    }

    public String getDetailsValueAmount() {
        return Objects.isNull(getDetailsValue()) ? null : getDetailsValue().amount;
    }

    public String getDetailsValueCurrency() {
        return Objects.isNull(getDetailsValue()) ? null : getDetailsValue().currency;
    }

    private Details.Value getDetailsValue() {
        return Objects.isNull(details) ? null : details.value;
    }

    public String getOtherAccountNumber() {
        return Objects.isNull(other_account) ? null : other_account.number;
    }

    public String getThisAccountId() {
        return Objects.isNull(this_account) ? null : this_account.id;
    }

    public String getOtherAccountHolderName() {
        return Objects.isNull(other_account)
                ? null
                : Objects.isNull(other_account.holder)
                ? null
                : other_account.holder.name;
    }

    public String getOtherAccountMetadataImageUrl() {
        return Objects.isNull(other_account)
                ? null
                : Objects.isNull(other_account.metadata)
                ? null
                : other_account.metadata.image_URL;
    }

}
