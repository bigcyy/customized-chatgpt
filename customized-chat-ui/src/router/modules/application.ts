const applicationRouter = {
    path: '/application',
    name: 'application',
    component: () => import('@/views/setting.vue'),
    meta: {
        title: '应用',
        icon: 'application',
    }
}

export default applicationRouter