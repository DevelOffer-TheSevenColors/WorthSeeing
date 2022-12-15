package kr.worthseeing.main.reservation.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QReservationUserId is a Querydsl query type for ReservationUserId
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QReservationUserId extends EntityPathBase<ReservationUserId> {

    private static final long serialVersionUID = -1807532666L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QReservationUserId reservationUserId = new QReservationUserId("reservationUserId");

    public final QReservation reservation;

    public final NumberPath<Integer> reservation_seq = createNumber("reservation_seq", Integer.class);

    public final NumberPath<Integer> ReservationUserId_seq = createNumber("ReservationUserId_seq", Integer.class);

    public final DateTimePath<java.util.Date> reservationUserIdDate = createDateTime("reservationUserIdDate", java.util.Date.class);

    public final NumberPath<Integer> userid = createNumber("userid", Integer.class);

    public final kr.worthseeing.users.entity.QUsers users;

    public QReservationUserId(String variable) {
        this(ReservationUserId.class, forVariable(variable), INITS);
    }

    public QReservationUserId(Path<? extends ReservationUserId> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QReservationUserId(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QReservationUserId(PathMetadata metadata, PathInits inits) {
        this(ReservationUserId.class, metadata, inits);
    }

    public QReservationUserId(Class<? extends ReservationUserId> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.reservation = inits.isInitialized("reservation") ? new QReservation(forProperty("reservation"), inits.get("reservation")) : null;
        this.users = inits.isInitialized("users") ? new kr.worthseeing.users.entity.QUsers(forProperty("users"), inits.get("users")) : null;
    }

}

