package kr.worthseeing.main.reservation.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QReservationUsers is a Querydsl query type for ReservationUsers
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QReservationUsers extends EntityPathBase<ReservationUsers> {

    private static final long serialVersionUID = -1582328120L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QReservationUsers reservationUsers = new QReservationUsers("reservationUsers");

    public final QReservation reservation;

    public final DateTimePath<java.util.Date> reservationUserIdDate = createDateTime("reservationUserIdDate", java.util.Date.class);

    public final NumberPath<Integer> ReservationUsers_seq = createNumber("ReservationUsers_seq", Integer.class);

    public final kr.worthseeing.users.entity.QUsers users;

    public QReservationUsers(String variable) {
        this(ReservationUsers.class, forVariable(variable), INITS);
    }

    public QReservationUsers(Path<? extends ReservationUsers> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QReservationUsers(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QReservationUsers(PathMetadata metadata, PathInits inits) {
        this(ReservationUsers.class, metadata, inits);
    }

    public QReservationUsers(Class<? extends ReservationUsers> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.reservation = inits.isInitialized("reservation") ? new QReservation(forProperty("reservation"), inits.get("reservation")) : null;
        this.users = inits.isInitialized("users") ? new kr.worthseeing.users.entity.QUsers(forProperty("users")) : null;
    }

}

