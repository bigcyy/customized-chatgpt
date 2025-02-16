const knowledgeRouter = {
    path: '/knowledge',
    name: 'knowledge',
    component: () => import('@/views/setting.vue'),
    meta: {
        title: '知识库',
        icon: 'knowledge',
    }
}

export default knowledgeRouter