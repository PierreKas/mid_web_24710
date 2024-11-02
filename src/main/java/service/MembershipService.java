//package service;
//
//import dao.MembershipDao;
//import model.Membership;
//
//public class MembershipService {
//    private MembershipDao membershipDao;
//
//    public MembershipService() {
//        membershipDao = new MembershipDao();
//    }
//
//    public boolean registerMembership(String userId, String membershipType) {
//        Membership membership = new Membership(userId, membershipType);
//        return membershipDao.save(membership);
//    }
//}
//
