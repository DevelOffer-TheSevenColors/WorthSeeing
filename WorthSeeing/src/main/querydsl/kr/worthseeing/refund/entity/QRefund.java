package kr.worthseeing.refund.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QRefund is a Querydsl query type for Refund
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QRefund extends EntityPathBase<Refund> {

    private static final long serialVersionUID = -790513595L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QRefund refund = new QRefund("refund");

    public final kr.worthseeing.blockgroup.entity.QBlockGroup blockGroup;

    public final kr.worthseeing.blockGroupReservation.entity.QBlockGroupReservaton blockGroupReservation;

    public final NumberPath<Integer> refund_seq = createNumber("refund_seq", Integer.class);

    public final DateTimePath<java.util.Date> refundDate = createDateTime("refundDate", java.util.Date.class);

    public final NumberPath<Integer> refundPrice = createNumber("refundPrice", Integer.class);

    public final kr.worthseeing.status.entity.QStatus status;

    public QRefund(String variable) {
        this(Refund.class, forVariable(variable), INITS);
    }

    public QRefund(Path<? extends Refund> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QRefund(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QRefund(PathMetadata metadata, PathInits inits) {
        this(Refund.class, metadata, inits);
    }

    public QRefund(Class<? extends Refund> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.blockGroup = inits.isInitialized("blockGroup") ? new kr.worthseeing.blockgroup.entity.QBlockGroup(forProperty("blockGroup"), inits.get("blockGroup")) : null;
        this.blockGroupReservation = inits.isInitialized("blockGroupReservation") ? new kr.worthseeing.blockGroupReservation.entity.QBlockGroupReservaton(forProperty("blockGroupReservation"), inits.get("blockGroupReservation")) : null;
        this.status = inits.isInitialized("status") ? new kr.worthseeing.status.entity.QStatus(forProperty("status")) : null;
    }

}

